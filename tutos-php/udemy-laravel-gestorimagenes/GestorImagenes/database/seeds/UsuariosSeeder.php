<?php
    /**
     * Created by PhpStorm.
     * User: Alejo Saa G
     * Date: 13/03/2017
     * Time: 14:40
     */
    use GestorImagenes\Usuario;
    use Illuminate\Database\Seeder;
    use Illuminate\Database\Eloquent\Model;

    class UsuariosSeeder extends Seeder
    {

        /**
         * Run the database seeds.
         *
         * @return void
         */
        public function run()
        {
            Usuario::truncate();

            for($i = 0; $i < 10; $i++) {
                Usuario::create([
                                    'nombre' => 'user' . $i,
                                    'email' => 'jhon' . $i . '@doe.com',
                                    'password' => bcrypt('pass' . $i),
                                    'pregunta' => 'pregunta ' . $i,
                                    'respuesta' => 'respuesta ' . $i
                                ]);
            }
        }

    }