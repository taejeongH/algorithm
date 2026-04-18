#include <iostream>
using namespace std;

struct Person {
    string name;
    int year;
    int month;
    int day;
};

bool comparePerson(const Person& a, const Person& b) {
    if (a.year != b.year) return a.year < b.year;
    if (a.month != b.month) return a.month < b.month;
    return a.day < b.day;
}

void mergeSort(Person p[], int l, int r) {
    if (l >= r) return;

    int mid = (l + r) / 2;

    mergeSort(p, l, mid);
    mergeSort(p, mid + 1, r);

    int leftSize = mid - l + 1;
    int rightSize = r - mid;

    Person L[100];
    Person R[100];

    for (int i = 0; i < leftSize; i++) {
        L[i] = p[l + i];
    }
    for (int i = 0; i < rightSize; i++) {
        R[i] = p[mid + 1 + i];
    }

    int lidx = 0, ridx = 0, idx = l;

    while (lidx < leftSize && ridx < rightSize) {
        if (comparePerson(L[lidx], R[ridx])) {
            p[idx++] = L[lidx++];
        }
        else {
            p[idx++] = R[ridx++];
        }
    }

    while (lidx < leftSize) {
        p[idx++] = L[lidx++];
    }

    while (ridx < rightSize) {
        p[idx++] = R[ridx++];
    }
}

int main() {
    int N;
    cin >> N;

    Person p[100];

    for (int i = 0; i < N; i++) {
        cin >> p[i].name >> p[i].day >> p[i].month >> p[i].year;
    }

    mergeSort(p, 0, N - 1);


    cout << p[N-1].name << '\n' << p[0].name;

}