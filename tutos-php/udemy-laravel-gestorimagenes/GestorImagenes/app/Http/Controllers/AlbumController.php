<?php namespace GestorImagenes\Http\Controllers;

use GestorImagenes\Album;
use GestorImagenes\Http\Requests;
use GestorImagenes\Http\Controllers\Controller;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AlbumController extends Controller
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

    function getIndex()
    {
        $usuario = Auth::user();
        $albumes = $usuario->albumes;

        return view('albumes.mostrar', ['albumes' => $albumes]);
    }

    function getCrearAlbum()
    {
        return view('albumes.crear-album');
    }

    function postCrearAlbum(Requests\CrearAlbumRequest $request)
    {
        $usuario = Auth::user();

        Album::create([
                          'nombre' => $request->get('nombre'),
                          'descripcion' => $request->get('descripcion'),
                          'usuario_id' => $usuario->id
                      ]);

        return redirect('/validado/albumes')->with('creado', 'El album ha sido creado');
    }

    function getActualizarAlbum($id)
    {
        $album = Album::find($id);

        return view('albumes.actualizar-album', ['album' => $album]);
    }

    function postActualizarAlbum(Requests\ActualizarAlbumRequest $request)
    {
        $album = Album::find($request->get('id'));
        $album->nombre = $request->get('nombre');
        $album->descripcion = $request->get('descripcion');

        $album->save();

        return redirect('/validado/albumes')->with('actualizado', 'El album se actualizo.');
    }

    /*function getEliminarAlbum()
    {
        return 'formulario eliminar album';
    }*/

    function postEliminarAlbum(Requests\EliminarAlbumRequest $request)
    {
        $album = Album::find($request->get('id'));

        $nombre = $album->nombre;

        $fotos = $album->fotos;
        foreach($fotos as $foto) {
            $rutaanterior = getcwd() . $foto->ruta;
            /*if(file_exists($rutaanterior)) {
                unlink(realpath($rutaanterior));
            }*/

            $foto->delete();
        }
        $album->delete();

        return redirect('/validado/albumes')->with('eliminado', "El album {$nombre} fue eliminado.");
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
