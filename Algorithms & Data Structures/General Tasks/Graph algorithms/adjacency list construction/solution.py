with open("input.txt") as fin:
    n, m = [int(x) for x in fin.readline().split()]
    a = [[] for i in range(n)]
    for _i in range(m):
        x, y = [int(tmp) - 1 for tmp in fin.readline().split()]
        a[x].append(y)
        a[y].append(x)
    with open("output.txt", 'w') as fout:
        for i in range(len(a)):
            fout.write(str(len(a[i])))
            for j in range(len(a[i])):
                fout.write(' ' + str(a[i][j] + 1))
            fout.write('\n')