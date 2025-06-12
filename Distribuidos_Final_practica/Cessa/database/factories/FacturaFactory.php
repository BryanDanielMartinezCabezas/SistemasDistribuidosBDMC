<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

class FacturaFactory extends Factory
{
    public function definition(): array
    {
        return [
            'empresa' => 'Cessa',
            'ci' => $this->faker->numerify('#######'),
            'nombre_completo' => $this->faker->name(),
            'periodo' => $this->faker->randomElement(['2024-01', '2024-02', '2024-03']),
            'monto' => $this->faker->randomFloat(2, 50, 500),
            'estado' => 'Pendiente',
        ];
    }
}

