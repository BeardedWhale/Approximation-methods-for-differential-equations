# Approximation-methods-for-differential-equations
This program implements three approximation methods for building a chart of differential equation. Methods: Euler, Improved Euler, Runge-Kutta

Implemented for : y' = cosx - y; x0 = 1; y(x0) = 1; [1;9,5]

Building function using:

For Euler method: yi+1 = yi + hf(xi, yi) where f(x,y) = y'
For Improved Euler: yi+1 = yi + 0.5h(f(xi, yi) + f(xi+1, yi + hf(xi, yi)))
For Runge-Kutta method: 
k1i = f(xi, yi);
k2i = f(xi + h/2 , yi + h/2k1i); 
k3i = f(xi + h/2 , yi + h/2k2i); 
k4i = f(xi+h, yi +hk3i);
yi=yi-1 + h*(k1i + 2*k2i + 2*k3i + k4i)/6;

![Function chart screenshot](https://github.com/BeardedWhale/Approximation-methods-for-differential-equations/blob/master/function%20chart.png)

Error chart example:

![Error chart screenshot](https://github.com/BeardedWhale/Approximation-methods-for-differential-equations/blob/master/Error%20chart.png)

Trauncation error chart screenshot:

![Truncation error chart](https://github.com/BeardedWhale/Approximation-methods-for-differential-equations/blob/master/truncation%20chart.png)

