#include <iostream>
#include <fstream>

using namespace std;

class DSU {
public:
	int* size;
	int* parent;
	int count;

	DSU(int n) {
		size = new int[n];
		parent = new int[n];
		count = n;
		for (int i = 0; i < n; i++) {
			size[i] = 1;
			parent[i] = i;
		}
	}

	int find_set(int x) {
		if (x == this->parent[x]) return x;
		parent[x] = find_set(parent[x]);
		return parent[x];
	}

	void uni(int x, int y) {
		x = find_set(x);
		y = find_set(y);
		if (x != y) {
			--count;
			if (size[x] < size[y]) swap(x, y);
			parent[y] = x;
			++size[x];
		}
	}
};

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n, q;
	in >> n >> q;
	DSU dsu = DSU(n);
	for (int i = 0; i < q; i++) {
		int x, y;
		in >> x >> y;
		dsu.uni(x - 1, y - 1);
		out << dsu.count << '\n';
	}
	in.close();
	out.close();
	return 0;
}