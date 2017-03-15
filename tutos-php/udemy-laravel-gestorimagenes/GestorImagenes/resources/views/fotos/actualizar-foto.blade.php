@extends('app')

@section('content')
    <div class="container-fluid">
        <form action="{{url('/validado/fotos/actualizar-foto')}}" method="post" role="form" class="form-horizontal" enctype="multipart/form-data">
            <input type="hidden" name="_token" value="{{csrf_token()}}" required />
            <input type="hidden" name="id" value="{{$foto->id}}">

            <div class="form-group required required">
                <label for="nombre" class="col-md-4 control-label">Nombre</label>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="nombre" value="{{$foto->nombre}}" required/>
                </div>
            </div>

            <div class="form-group required">
                <label for="nombre" class="col-md-4 control-label">Descripción</label>
                <div class="col-md-6">
                    <textarea name="descripcion" id="descripcion" cols="30" rows="3" class="form-control">{{$foto->descripcion}}</textarea>
                </div>
            </div>

            <div class="form-group required">
                <label class="col-md-4 control-label">Imagen max: 20MB</label>
                <div class="col-md-6">
                    <input type="file" class="form-control" name="imagen">
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-6 col-md-offset-4">
                    <button type="submit" class="btn btn-primary">
                        Actualizar imagen
                    </button>
                </div>
            </div>
        </form>
    </div>
@endsection