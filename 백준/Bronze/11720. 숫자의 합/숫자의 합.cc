#include <iostream>
using namespace std;

int main() {

	int N;

	cin >> N;

	char* s = new char[N + 1];
	int sum = 0;

	cin >> s;

	int len = 0;
	while (s[len] != '\0') {
		sum += s[len++] - '0';
	}

	cout << sum;

	return 0;
}