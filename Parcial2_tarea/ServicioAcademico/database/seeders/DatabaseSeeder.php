<?php

namespace Database\Seeders;

use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use App\Models\Titulo;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
public function run(): void
{
    Titulo::create([
        'ci' => '12345678',
        'nombres' => 'Juan',
        'primer_apellido' => 'Pérez',
        'segundo_apellido' => 'García',
        'titulo' => 'Ingeniería de Sistemas',
        'fecha_emision' => '2024-12-01',
    ]);

    Titulo::create([
        'ci' => '87654321',
        'nombres' => 'María',
        'primer_apellido' => 'Lopez',
        'segundo_apellido' => 'Fernandez',
        'titulo' => 'Derecho',
        'fecha_emision' => '2023-06-15',
    ]);
}
}
