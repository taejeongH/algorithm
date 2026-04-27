#include<iostream>
#define MAX 100

using namespace std;

void mergeSort(long long arr[], int l, int r) {
	if (l == r) return;

	int mid = (l + r) / 2;
	mergeSort(arr, l, mid);
	mergeSort(arr, mid + 1, r);

	int lSize = mid - l + 1;
	int rSize = r - mid;
	int* L = new int[lSize];
	int* R = new int[rSize];

	for (int i = 0; i < lSize; i++) {
		L[i] = arr[l + i];
	}

	for (int i = 0; i < rSize; i++) {
		R[i] = arr[mid + 1 + i];
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

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin >> T;

	for (test_case = 1; test_case <= T; ++test_case)
	{
		int N;
		long long arr[(MAX * (MAX - 1)) / 2];

		cin >> N;
		
		for (int i = 0; i < (N*(N-1))/2; i++) cin >> arr[i];

		mergeSort(arr, 0, (N * (N - 1)) / 2-1);

		long long min = 0;
		for (int i = 0; i < N - 1; i++) min += arr[i];

		long long max = 0;
		int idx = (N*(N - 1))/2;
		int minus = N - 1;

		while (true) {
			idx -= minus;
			max += arr[idx];
			if (idx == 0) break;
			minus--;
		}

		cout << min << ' ' << max << '\n';
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}