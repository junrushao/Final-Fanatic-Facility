str = ""
with open("me.out", "r") as infile:
	for line in infile.readlines()[5:]:
		str += line
with open("me.out", "w") as outfile:
	outfile.write(str)
