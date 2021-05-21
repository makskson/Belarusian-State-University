with open("input.txt") as fin:
    n, m = [int(x) for x in fin.readline().split()]
    a = [[0] * n for i in range(n)]
    for _i in range(m):
        x, y = [int(x) for x in fin.readline().split()]
        a[x - 1][y - 1] = 1
        a[y - 1][x - 1] = 1
    with open("output.txt", 'w') as fout:
        for i in range(n):
            for j in range(n):
                fout.write(str(a[i][j]) + " ")
            fout.write('\n')