#include<iostream>
#define MAX 1000000
using namespace std;

int arr[MAX];

void mergeSort(int l, int r) {
	if (l == r) return;

	int mid = (l + r) / 2;

	mergeSort(l, mid);
	mergeSort(mid + 1, r);

	int lSize = mid - l + 1;
	int rSize = r - mid;

	int* L = new int[lSize];
	int* R = new int[rSize];
	for (int i = 0; i < lSize; i++) {
		L[i] = arr[i + l];
	}

	for (int i = 0; i < rSize; i++) {
		R[i] = arr[i + mid + 1];
	}

	
	int lIdx = 0;
	int rIdx = 0;
	int idx = l;

	while (lIdx < lSize && rIdx < rSize) {
		if (L[lIdx] < R[rIdx]) {
			arr[idx++] = L[lIdx++];
		}
		else {
			arr[idx++] = R[rIdx++];
		}
	}

	while (lIdx < lSize) {
		arr[idx++] = L[lIdx++];
	}

	while (rIdx < rSize) {
		arr[idx++] = R[rIdx++];
	}
}

int main() {
	int N;

	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	mergeSort(0, N-1);

	for (int i = 0; i < N; i++) {
		cout << arr[i] << '\n';
	}

	return 0;
}