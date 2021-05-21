def check(arr):
    for i in range(len(arr)):
        if not arr[i]:
            return True
    return False


def getNode(arr):
    for i in range(len(arr)):
        if not arr[i]:
            arr[i] = True
            return i
    return -1


def mark(m, arr, i, st):
    for j in range(len(arr)):
        if m[i][j] and not arr[j]:
            st.append(j)
            arr[j] = True


fin = open('input.txt')
n = int(fin.readline())
a, v, stack, metka, num = [], [False for i in range(n)], [], [0 for i in range(n)], 1
for i in range(n):
    a.append([int(x) for x in fin.readline().split()])
fin.close()
while check(v):
    stack.append(getNode(v))
    while stack:
        curNode = stack.pop(0)
        metka[curNode] = num
        num += 1
        mark(a, v, curNode, stack)
with open('output.txt', 'w') as fout:
    for item in metka:
        fout.write(str(item) + ' ')