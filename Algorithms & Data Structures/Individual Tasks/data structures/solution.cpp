#include <iostream>
#include <vector>

using namespace std;

int index = 0;

int fmin(vector<int> a, int l, int r) {
	int mini = a[l];
	for (int i = l + 1; i < r; i++) {
		if (a[i] < mini) mini = a[i];
	}
	return mini;
}

class Node {
public:
	Node* parent;
	Node* left;
	Node* right;
	Node* rightChild;
	Node* leftChild;
	int key;

	Node() {
		parent = NULL;
		left = NULL;
		right = NULL;
		leftChild = NULL;
		rightChild = NULL;
		key = 0;
	}
	Node(Node* par, int key1) {
		parent = par;
		left = NULL;
		right = NULL;
		leftChild = NULL;
		rightChild = NULL;
		key = key1;
	}

};

class Tree {
public:
	Node* root;

	Tree() {
		root = NULL;
	}

	Node* build(Node* par, int l, int r, Node ** nodes) {
		Node* curNode = new Node(par, 0);
		if (r - l == 1) {
			curNode = nodes[l];
			curNode->parent = par;
			return curNode;
		}
		curNode->leftChild = build(curNode, l, (l + r) / 2, nodes);
		curNode->rightChild = build(curNode, (l + r) / 2, r, nodes);
		curNode->key = min(curNode->leftChild->key, curNode->rightChild->key);
		return curNode;
	}

	void pot(Node* node) {
		if (node == NULL) {
			return;
		}
		cout << node->key << " ";
		pot(node->leftChild);
		pot(node->rightChild);
	}

	void optimize(Node* node) {
		while (((node->leftChild && !node->rightChild) || (!node->leftChild && node->rightChild)) && node->parent) { // пока отсутствует один из детей и есть родитель
			if (!node->leftChild) {
				node->rightChild->parent = node->parent;
				if (node->parent->rightChild == node) 
					node->parent->rightChild = node->rightChild;
				else 
					node->parent->leftChild = node->rightChild;
			} else {
				node->leftChild->parent = node->parent;
				if (node->parent->rightChild == node) node->parent->rightChild = node->leftChild;
				else node->parent->leftChild = node->leftChild;
			}
			node = node->parent;
		}
		while (node) {
			if (!node->leftChild) node->key = node->rightChild->key;
			else if (!node->rightChild) node->key = node->leftChild->key;
			else node->key = min(node->leftChild->key, node->rightChild->key);
			node = node->parent;
		}
	}

	int work() {
		int minimum = root->key;
		Node* tmp = root;
		while (tmp->leftChild || tmp->rightChild) {
			while (tmp->leftChild && tmp->leftChild->key == minimum) {
				tmp = tmp->leftChild;
			}
			while (tmp->rightChild && tmp->rightChild->key == minimum) {
				tmp = tmp->rightChild;
			}
		}
		int delta;
		if (!tmp->left) {
			delta = tmp->right->key - minimum;
		} else if (!tmp->right) {
			delta = tmp->left->key - minimum;
		} else {
			delta = min(tmp->right->key, tmp->left->key) - minimum;
		}
		tmp->key += delta;
		if (tmp->right && tmp->right->key == tmp->key) {
			Node* tmp1 = tmp->right->parent;
			Node* tmp2 = tmp->right;
			tmp->right = tmp->right->right;
			if (tmp->right) tmp->right->left = tmp;
			if (tmp1->leftChild == tmp2) tmp1->leftChild = NULL;
			else tmp1->rightChild = NULL;
			optimize(tmp1);
		}
		if (tmp->left && tmp->left->key == tmp->key) {
			Node* tmp1 = tmp->left->parent;
			Node* tmp2 = tmp->left;
			tmp->left = tmp->left->left;
			if (tmp->left) tmp->left->right = tmp;
			if (tmp1->leftChild == tmp2) tmp1->leftChild = NULL;
			else tmp1->rightChild = NULL;
			optimize(tmp1);
		}
		optimize(tmp->parent);
		return delta;
	}
};

int main() {
	int n, maximum;
	cin >> n;
	vector<int> a;
	do {
		int tmp;
		cin >> tmp;
		maximum = tmp;
		a.push_back(tmp);
	} while (1 == 0);
	for (int i = 1; i < n; i++) {
		int tmp;
		cin >> tmp;
		if (tmp != a.back()) {
			if (maximum < tmp) maximum = tmp;
			a.push_back(tmp);
		}
	}
	Node** nodes = new Node * [a.size()];
	for (int i = 0; i < a.size(); i++) {
		nodes[i] = new Node(NULL, a[i]);
		if (i != 0) {
			nodes[i]->left = nodes[i - 1];
			nodes[i]->left->right = nodes[i];
		}
	}

	Tree tree = Tree();
	tree.root = (tree.build(NULL, 0, a.size(), nodes));
	long long count = 0;
	while (tree.root->key != maximum) {
		count += tree.work();
	}
	cout << count << endl;
	return 0;
}