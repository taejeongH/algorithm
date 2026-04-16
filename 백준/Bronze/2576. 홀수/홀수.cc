#include <iostream>
using namespace std;

int main() {
	int N = 7;


	int num, min, sum;

	sum = 0;
	min = 100;
	for (int i = 0; i < N; i++) {
		cin >> num;

		if ((num & 1) == 1) {
			sum += num;
			if (num < min) {
				min = num;
			}
		}
	}

	if (min != 100) cout << sum << '\n' << min;
	else cout << -1;
}

