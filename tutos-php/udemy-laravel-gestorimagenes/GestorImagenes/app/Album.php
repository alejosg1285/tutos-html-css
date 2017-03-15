<?php namespace GestorImagenes;

use Illuminate\Database\Eloquent\Model;

class Album extends Model
{
    //
    protected $table = 'albumes';

    protected $fillable = ['id', 'nombre', 'descripcion', 'usuario_id'];

    function fotos()
    {
        return $this->hasMany('GestorImagenes\Foto');
    }

    function propietario()
    {
        return $this->belongsTo('GestorImagenes\User');
    }
}
