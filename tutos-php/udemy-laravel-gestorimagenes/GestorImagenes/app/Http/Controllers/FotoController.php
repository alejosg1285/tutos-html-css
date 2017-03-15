<?php namespace GestorImagenes\Http\Controllers;

use Carbon\Carbon;
use GestorImagenes\Album;
use GestorImagenes\Foto;
use GestorImagenes\Http\Requests;
use GestorImagenes\Http\Controllers\Controller;

use GestorImagenes\Http\Requests\MostrarFotosRequest;
use Illuminate\Http\Request;

class FotoController extends Controller
{

    /**
     * Display a listing of the resource.
     *
     * @return Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @return Response
     */
    public function store()
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function update($id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function destroy($id)
    {
        //
    }

    function getIndex(MostrarFotosRequest $request)
    {
        $id = $request->get('id');
        $fotos = Album::find($id)->fotos;

        return view('fotos.mostrar', ['fotos' => $fotos, 'id' => $id]);
    }

    function getCrearFoto(Request $request)
    {
        $id = $request->get('id');

        return view('fotos.crear-foto', ['id' => $id]);
    }

    function postCrearFoto(Requests\CrearFotoRequest $request)
    {
        $imagen = $request->file('imagen');
        $ruta = '/img/';
        $nombre = sha1(Carbon::now()) . '.' . $imagen->guessExtension();

        $imagen->move(getcwd() . $ruta, $nombre);

        Foto::create([
                         'nombre' => $request->get('nombre'),
                         'descripcion' => $request->get('descripcion'),
                         'ruta' => $ruta . $nombre,
                         'album_id' => $request->get('id')
                     ]);

        return redirect("/validado/fotos?id={$request->get('id')}")->with('creada', 'La foto ha sido subida');
    }

    function getActualizarFoto($id)
    {
        $foto = Foto::find($id);

        return view('fotos.actualizar-foto', ['foto' => $foto]);
    }

    function postActualizarFoto(Requests\ActualizarFotoRequest $request)
    {
        $foto = Foto::find($request->get('id'));
        $foto->nombre = $request->get('nombre');
        $foto->descripcion = $request->get('descripcion');

        if($request->hasFile('imagen')) {
            $imagen = $request->file('imagen');
            $ruta = '/img/';
            $nombre = sha1(Carbon::now()) . '.' . $imagen->guessExtension();

            $imagen->move(getcwd() . $ruta, $nombre);

            $rutaanterior = getcwd() . $foto->ruta;

            /*if(file_exists($rutaanterior))
                unlink(realpath($rutaanterior));*/

            $foto->ruta = $ruta . $nombre;
        }

        $foto->save();

        return redirect("/validado/fotos?id={$foto->album_id}")->with('editada', 'La foto se actualizo.');
    }

    /*function getEliminarFoto()
    {
        return 'formulario eliminar foto';
    }*/

    function postEliminarFoto(Requests\EliminarFotoRequest $request)
    {
        $foto = Foto::find($request->get('id'));

        $nombre=$foto->nombre;

        $rutaanterior = getcwd() . $foto->ruta;

        /*if(file_exists($rutaanterior))
            unlink(realpath($rutaanterior));*/

        $foto->delete();

        return redirect("/validado/fotos?id={$foto->album_id}")->with('eliminada', "La foto {$nombre} se ha eliminado.");
    }

    /**
     * Handle calls to missing methods on the controller.
     *
     * @param  array $parameters
     *
     * @return mixed
     *
     * @throws \Symfony\Component\HttpKernel\Exception\NotFoundHttpException
     */
    public function missingMethod($parameters = [])
    {
        abort(404);
    }
}
