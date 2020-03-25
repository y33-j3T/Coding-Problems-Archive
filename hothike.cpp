// #include <iostream>
#include <bits/stdc++.h>

using namespace std;

int main() {
    int MAX_TEMPERATURE = 80;
    int n;
    cin >> n;

    int temperature[n];
    for (int i = 0; i < n; i++) {
        cin >> temperature[i];
    }

    int resTemperature1, resTemperature2;
    int minTemperature = MAX_TEMPERATURE;
    int minTemperatureIdx = 0;
    for (int i = 2; i < n; i++) {
        int temperature1 = temperature[i - 2];
        int temperature2 = temperature[i];

        if (temperature2 < minTemperature) {
            minTemperatureIdx = i;
            resTemperature1 = temperature1;
            resTemperature2 = temperature2;
        }
    }

    cout << minTemperatureIdx + 1 << " ";
    cout << (resTemperature1 > resTemperature2 ? resTemperature1 : resTemperature2);
}
