<?php
    /**
     * Created by PhpStorm.
     * User: Alejo Saa G
     * Date: 19/06/2017
     * Time: 14:25
     */
    error_reporting(E_ALL);
    ini_set('display_errors', 1);

    //CORS
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: GET");
    header("Content-Type: application/json");

    $rutaBase = '../archivos';

    $ficheros = scandir($rutaBase);

    $resultados = [];

    foreach($ficheros as $key => $value) {
        $infoArchivo = [];
        $infoArchivo['nombre'] = $value;
        $infoArchivo['tipo'] = obtenerExtension($rutaBase . '/' . $value);
        $infoArchivo['tamano'] = convertirBytes(filesize($rutaBase . '/' . $value));
        $infoArchivo['ruta'] = $rutaBase;
        $infoArchivo['raiz'] = dirname($rutaBase);

        if($value != '.' && $value != '..') {
            array_push($resultados, $infoArchivo);
        }
    }

    function obtenerExtension($nombreArchivo)
    {
        $tipo = filetype($nombreArchivo);

        if($tipo == 'file') {
            return substr($nombreArchivo, strripos($nombreArchivo, '.') + 1);
        }

        return $tipo;
    }

    function convertirBytes($bytes)
    {
        $decimales = 0;
        $unidades = ['B', 'KB', 'MB', 'GB'];
        $exp = floor(log($bytes, 1024)) | 0;

        return round($bytes / (pow(1024, $exp)), $decimales) . $unidades[$exp];
    }

    print_r(stripslashes(json_encode($resultados)));