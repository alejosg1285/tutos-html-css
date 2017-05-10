<?php
    /**
     * Created by PhpStorm.
     * User: Alejo Saa G
     * Date: 13/03/2017
     * Time: 15:01
     */
    use GestorImagenes\Album;
    use GestorImagenes\Usuario;
    use Illuminate\Database\Seeder;

    class AlbumesSeeder extends Seeder
    {

        /**
         * Run the database seeds.
         *
         * @return void
         */
        public function run()
        {
            Album::truncate();
            $usuarios = Usuario::all();

            $contador = 0;
            foreach($usuarios as $usuario) {
                $cantidad = mt_rand(0, 5);
                for($i = 0; $i < $cantidad; $i++) {
                    Album::create([
                                      'nombre' => 'Album ' . $contador,
                                      'descripcion' => 'Descripcion album ' . $contador,
                                      'usuario_id' => $usuario->id
                                  ]);
                    $contador++;
                }
            }
        }

    }