<?php
    /**
     * Created by PhpStorm.
     * User: Alejo Saa G
     * Date: 13/03/2017
     * Time: 15:16
     */
    use GestorImagenes\Album;
    use GestorImagenes\Foto;
    use Illuminate\Database\Seeder;

    class FotosSeeder extends Seeder
    {

        /**
         * Run the database seeds.
         *
         * @return void
         */
        public function run()
        {
            Foto::truncate();
            $albumes = Album::all();

            $contador = 0;
            foreach($albumes as $album) {
                $cantidad = mt_rand(0, 5);
                for($i = 0; $i < $cantidad; $i++) {
                    Foto::create([
                                     'nombre' => 'Foto ' . $contador,
                                     'descripcion' => 'Descripcion foto ' . $contador,
                                     'ruta' => '/img/text.png',
                                     'album_id' => $album->id
                                 ]);
                    $contador++;
                }
            }
        }

    }