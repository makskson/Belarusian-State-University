#include <iostream>
#include <fstream>
#include <cmath>

using namespace std;

const int MAX_VALUE = 2147483647;

int main() {
	ifstream in("input.txt");
	int size;
	in >> size;
	int* n = new int[size];
	int* m = new int[size];
	for (int i = 0; i < size; i++) {
		in >> n[i] >> m[i];
	}
	in.close();

	int** a = new int* [size];
	for (int i = 0; i < size; i++) {
		a[i] = new int[size];
		a[i][i] = 0;
	}
	
	for (int l = 1; l < size; l++) {
		for (int i = 0; i < size - l; i++) {
			int j = i + l;
			a[i][j] = MAX_VALUE;
			for (int k = i; k < j; k++) {
				a[i][j] = min(a[i][j], a[i][k] + a[k + 1][j] + n[i] * m[k] * m[j]);
			}
		}
	}
	ofstream out("output.txt");
	out << a[0][size - 1];
	out.close();
	return 0;
}