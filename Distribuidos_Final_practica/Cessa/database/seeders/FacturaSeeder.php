<?php
namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Factura;

class FacturaSeeder extends Seeder
{
    public function run(): void
    {
        // Usuario 1234567 con 2 facturas
        Factura::create([
            'ci' => '1234567',
            'monto' => 150.00,
            'estado' => 'Pendiente',
        ]);

        Factura::create([
            'ci' => '1234567',
            'monto' => 75.50,
            'estado' => 'Pendiente',
        ]);

        // Usuario 7654321 con 1 factura pagada y una pendiente
        Factura::create([
            'ci' => '7654321',
            'monto' => 90.00,
            'estado' => 'Pagado',
        ]);

        Factura::create([
            'ci' => '7654321',
            'monto' => 120.00,
            'estado' => 'Pendiente',
        ]);
    }
}

namespace Database\Seeders;
