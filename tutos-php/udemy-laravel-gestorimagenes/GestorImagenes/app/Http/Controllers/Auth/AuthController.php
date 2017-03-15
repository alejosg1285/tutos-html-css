<?php namespace GestorImagenes\Http\Controllers\Auth;

use GestorImagenes\Http\Controllers\Controller;
use GestorImagenes\Http\Requests\IniciarSesionRequest;
use GestorImagenes\Http\Requests\RecuperarContrasenaRequest;
use GestorImagenes\Usuario;
use Illuminate\Contracts\Auth\Guard;
use Illuminate\Contracts\Auth\Registrar;
use Illuminate\Foundation\Auth\AuthenticatesAndRegistersUsers;
use Illuminate\Http\Request;

class AuthController extends Controller
{

    /*
    |--------------------------------------------------------------------------
    | Registration & Login Controller
    |--------------------------------------------------------------------------
    |
    | This controller handles the registration of new users, as well as the
    | authentication of existing users. By default, this controller uses
    | a simple trait to add these behaviors. Why don't you explore it?
    |
    */

    //use AuthenticatesAndRegistersUsers;
    /**
     * The Guard implementation.
     *
     * @var \Illuminate\Contracts\Auth\Guard
     */
    protected $auth;

    /**
     * The registrar implementation.
     *
     * @var \Illuminate\Contracts\Auth\Registrar
     */
    protected $registrar;

    /**
     * Show the application registration form.
     *
     * @return \Illuminate\Http\Response
     */
    public function getRegistro()
    {
        return view('auth.registro');
    }

    /**
     * Handle a registration request for the application.
     *
     * @param  \Illuminate\Http\Request $request
     *
     * @return \Illuminate\Http\Response
     */
    public function postRegistro(Request $request)
    {
        $validator = $this->registrar->validator($request->all());

        if($validator->fails()) {
            $this->throwValidationException(
                $request, $validator
            );
        }

        $this->auth->login($this->registrar->create($request->all()));

        return redirect($this->redirectPath());
    }

    /**
     * Show the application login form.
     *
     * @return \Illuminate\Http\Response
     */
    public function getInicio()
    {
        return view('auth.inicio');
    }

    /**
     * Handle a login request to the application.
     *
     * @param  \Illuminate\Http\Request $request
     *
     * @return \Illuminate\Http\Response
     */
    public function postInicio(IniciarSesionRequest $request)
    {
        $credentials = $request->only('email', 'password');

        if($this->auth->attempt($credentials, $request->has('remember'))) {
            return redirect()->intended($this->redirectPath());
        }

        return redirect($this->loginPath())
            ->withInput($request->only('email', 'remember'))
            ->withErrors([
                             'email' => $this->getFailedLoginMessage(),
                         ]);
    }

    /**
     * Get the failed login message.
     *
     * @return string
     */
    protected function getFailedLoginMessage()
    {
        return 'Correo o contraseña incorrectos.';
    }

    /**
     * Log the user out of the application.
     *
     * @return \Illuminate\Http\Response
     */
    public function getSalida()
    {
        $this->auth->logout();

        return redirect(property_exists($this, 'redirectAfterLogout') ? $this->redirectAfterLogout : '/');
    }

    /**
     * Get the post register / login redirect path.
     *
     * @return string
     */
    public function redirectPath()
    {
        if(property_exists($this, 'redirectPath')) {
            return $this->redirectPath;
        }

        return property_exists($this, 'redirectTo') ? $this->redirectTo : '/validado';
    }

    /**
     * Get the path to the login route.
     *
     * @return string
     */
    public function loginPath()
    {
        return property_exists($this, 'loginPath') ? $this->loginPath : '/validacion/inicio';
    }

    /**
     * Recuperar la contraseña del usuario.
     *
     * @return string
     */
    function getRecuperar()
    {
        return view('auth.recuperar');
    }

    function postRecuperar(RecuperarContrasenaRequest $request)
    {
        $email = $request->get('email');
        $pregunta = $request->get('pregunta');
        $respuesta = $request->get('respuesta');

        $usuario = Usuario::where('email', '=', $email)->first();

        if($pregunta === $usuario->pregunta && $respuesta === $usuario->respuesta) {
            $contrasena = $request->get('password');

            $usuario->password = bcrypt($contrasena);

            $usuario->save();

            return redirect('/auth/recuperar')->with(['recuperada' => 'Se registro la nueva contraseña']);
        }

        return redirect('/auth/recuperar')->withInput($request->only('email', 'pregunta'))->withErrors(['pregunta' => 'La pregunta y/o respuesta no coinciden']);
    }

    /**
     * Create a new authentication controller instance.
     *
     * @param  \Illuminate\Contracts\Auth\Guard $auth
     * @param  \Illuminate\Contracts\Auth\Registrar $registrar
     *
     * @return void
     */
    public function __construct(Guard $auth, Registrar $registrar)
    {
        $this->auth = $auth;
        $this->registrar = $registrar;

        $this->middleware('guest', ['except' => 'getSalida']);
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
