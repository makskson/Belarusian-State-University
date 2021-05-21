with open("input.txt") as fin:
    n = int(fin.readline())
    a = [0 for i in range(n)]
    for _i in range(n - 1):
        x, y = [int(x) - 1 for x in fin.readline().split()]
        a[y] = x + 1
    with open("output.txt", 'w') as fout:
        for i in range(n):
            fout.write(str(a[i]) + ' ')