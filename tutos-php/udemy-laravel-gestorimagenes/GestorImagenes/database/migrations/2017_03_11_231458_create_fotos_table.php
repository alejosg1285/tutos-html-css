<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateFotosTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('fotos', function(Blueprint $table)
		{
			$table->increments('id');
			$table->string('nombre', 60);
			$table->string('descripcion', 120);
			$table->string('ruta', 200);
			$table->integer('album_id')->unsigned();
			$table->foreign('album_id')->references('id')->on('albumes');
			$table->timestamps();
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('fotos');
	}

}
