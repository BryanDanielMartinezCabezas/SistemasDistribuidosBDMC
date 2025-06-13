<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Titulo extends Model
{
    /** @use HasFactory<\Database\Factories\TituloFactory> */
    use HasFactory;


    // app/Models/Titulo.php

protected $fillable = [
    'ci',
    'nombres',
    'primer_apellido',
    'segundo_apellido',
    'titulo',
    'fecha_emision',
];

}
