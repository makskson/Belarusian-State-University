#include <fstream>

using namespace std;

int main() {
	long long n;
	ifstream in("input.txt");
	in >> n;
	ofstream out("output.txt");
	if (n <= 0) {
		out << -1;
		return 0;
	}
	int i = 0;
	while (n != 0) {
		if (n % 2) out << i << endl;
		i++;
		n /= 2;
	}
	return 0;
}