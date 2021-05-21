with open("input.txt") as fin:
    n = int(fin.readline())
    a, b = [], [0 for i in range(n)]
    for i in range(n):
        a.append([int(x) for x in fin.readline().split()])
        for j in range(n):
            if a[i][j]:
                b[j] = i + 1
    with open("output.txt", 'w') as fout:
        for i in range(n):
            fout.write(str(b[i]) + ' ')