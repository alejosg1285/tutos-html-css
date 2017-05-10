<?php namespace GestorImagenes\Http\Controllers;

use GestorImagenes\Http\Requests;
use GestorImagenes\Http\Controllers\Controller;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class UsuarioController extends Controller
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

    function getEditarPerfil()
    {
        return view('usuarios.actualizar');
    }

    function postEditarPerfil(Requests\EditarPerfilRequest $request)
    {
        $usuario = Auth::user();

        $usuario->nombre = $request->get('nombre');

        if($request->has('password')) {
            $usuario->password = bcrypt($request->get('password'));
        }

        if($request->has('pregunta')) {
            $usuario->pregunta = $request->get('pregunta');
            $usuario->respuesta = $request->get('respuesta');
        }

        $usuario->save();

        return redirect('/validado')->with('actualizado', 'Su perfil ha sido actualizado.');
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
