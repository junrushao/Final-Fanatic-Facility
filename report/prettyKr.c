#include <stdio.h>
typedef int (*T)(int, int);
int lessThan(int a, /* asdasda*/ int b) {
	return a < b;
}
void quickSort(int a[], /*asdadsasadasdasd*/ /* QAQ T_T*/ int l, int r, /*we*/ T /*memeda*/ f) {
	int i = l, j = r, x = a[(l + r) / 2];
	while (i <= j) {
		while (f(a[i], x))
			i++;
		while (f(x, a[j]))
			j--;
		if (i <= j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			++i;
			--j;
		}
	}
	if (l < j)
		quickSort(a, l, j, f);
	if (i < r)
		quickSort(a, i, r, f);
/*	return 1;*/
}
int main() /* oh my lich main*/ {
/* oh my lich wengjian*/
	int a[10100];
	int n = 10000;
	int i;
	int localLessThan(int a, int b) {
		return a < b;
	}
	for (i = 1; i <= n; i++)
		a[i] = n + 1 - i;
	quickSort(a, 1, n, lessThan);
	quickSort(a, 1, n,
		[](int a, int b) -> int {
			return a < b;
		});
	quickSort(a, 1, n, localLessThan);
	for (i = 1; i <= n; i++)
		printf("%d ", a[i]);
	printf("\n");
	return 0;
}

