// #include <iostream>
#include <bits/stdc++.h>

using namespace std;

int main() {
    int n;
    cin >> n;

    int temp[n];
    for (int i = 0; i < n; i++) {
        cin >> temp[i];
    }

    int resIdx = 2;
    int minTemp = 40;
    int resFirst = temp[0];
    int resFinal = temp[2];
    for (int i = 2; i < n; i++) {
        int tempFirst = temp[i - 2];
        int tempFinal = temp[i];

        if ((tempFirst < minTemp && tempFinal < minTemp) &&
            tempFirst + tempFinal != resFirst + resFinal) {
            minTemp = max(tempFirst, tempFinal);
            resIdx = i;
            resFirst = tempFirst;
            resFinal = tempFinal;
        }
    }

    cout << resIdx - 1 << " ";
    cout << (resFirst > resFinal ? resFirst : resFinal);
}
