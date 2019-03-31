import sys

elements = []
totalsize = 0
lastelement = 0
for line in sys.stdin:
    linesplit = line.split("\t")[0]
    size = int(linesplit)
    label = line.split("\t")[1]
    elements.append((size, label))
    lastelement = size

totalsize = lastelement
for size, label in elements:
    print(str(("{0:0.2f}".format(size*100/totalsize)) + "% " + label.rstrip()))
