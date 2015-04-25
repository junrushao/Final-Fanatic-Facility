/** Target: Check the struct assignment.
 * Possible optimization: Dead code elimination, common expression, strength reduction, inline function
 * REMARK: Pay attention to the addressing of the struct. The struct assignment should be supported.
 *
**/

#include <stdio.h>
int size1=5,size2=5;
struct node
{
    int a[5][5];
    char ch[2];
    int count;
    struct inside{int p;} in;
} a[5];
int comp1(struct inside in1,struct inside in2)
{
    if (in1.p!=in2.p) return 0;
    else return 1;
}
int compare(struct node node1,struct node node2)
{
    int i,j=0;
    for (i=0;i<size1;++i)
        for (j=0;j<size1;++j)
            if (node1.a[i][j]!=node2.a[i][j]) return 0;
    if (node1.ch[0]!=node2.ch[0]) return 0;
    else if (node1.ch[1]!=node2.ch[1]) return 0;
    //else if (node1.count-node2.count!=0) return 0;
    else return comp1(node1.in,node2.in);
    return -1;
}
struct node getNode(int i)
{
    a[i].count++;
    return a[i];
}
void exchange(struct node *node1,struct node *node2)
{
    struct node temp=*node1;
    *node1=*node2;
    *node2=temp;
}
int comp(int i,int j)
{
    struct node node1=getNode(i),node2=getNode(j);
    int m=0,n=0;
    for (;m<size1;++m)
        for (;n<size1;++n)
            if (node1.a[m][n]>node2.a[m][n])
            {
                exchange(&node1,&node2);
                node1.in.p++;
                node2.in.p--;
            }
}
void show(int k)
{
    int i;
    for (i=0;i<size1;++i)
    {
        int j;
        for (j=0;j<size1;++j)
            printf("%d\t",a[k].a[i][j]);
        printf("\n");
    }
    printf("%c %c %d, %d\n",a[k].ch[0],a[k].ch[1],a[k].count,a[k].in.p);
}
int main()
{
    int q=7,k,i,j;
    for (k=0;k<size2;++k)
        for (i=0;i<size1;++i)
            for (j=0;j<size1;++j)
            {
                a[k].a[i][j]=(i*5110+j)%(34-k)+1,a[k].ch[0]=k+i*i*i,a[k].ch[1]=j+i+k<<1,a[k].in.p=k+~i|j;
                a[k].ch[0]=a[k].ch[0]%('z'-'a'+1)+'a';
                a[k].ch[1]=a[k].ch[1]%('Z'-'A'+1)+'A';
            }
    for (i=0;i<size2;++i)
        for (j=0;j<size2;++j)
            comp(i,j);
    for (k=0;k<size2;++k)
        show(k);
    for (i=size2-1;i>-1;i--)
        if (i%3==0) a[i]=getNode((i+3)%size2);
        else a[i]=getNode(i);
    printf("\n");
    for (i=0;i<size2;++i)
    {
        for (j=0;j<size2;++j)
            printf("%d ",compare(getNode(i),getNode(j)));
        printf("\n");
    }
    //system("pause");
    return 0;
}