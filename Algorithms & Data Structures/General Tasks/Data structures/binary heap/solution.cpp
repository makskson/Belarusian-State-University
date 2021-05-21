#include <iostream>
#include <fstream>

using namespace std;

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n;
	in >> n;
	int *a = new int[n];
	in >> a[0];
	for (int i = 1; i < n; i++) {
		in >> a[i];
		if (a[i] < a[(i-1)/2]) {
			out << "No";
			return 0;
		}
	}
	out << "Yes";
	return 0;
}