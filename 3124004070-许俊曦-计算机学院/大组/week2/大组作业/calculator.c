#include<stdio.h>
#include <stdlib.h>
#include <string.h>

//定义链栈节点的结构体
typedef struct StackNode {
    char data;
    struct StackNode *next;
} StackNode,*LinkStackPtr;

//定义链栈结构体
typedef struct LinkStack
{
    LinkStackPtr top;//栈顶指针
    int count;
}LinkStack;


//初始化一个空栈
void initStack(LinkStack *s){
    s->top = NULL;
    s->count = 0;
}

//判断链栈是否为空
int stackEmpty(LinkStack *s){
    return s->top == NULL;//如果为空则返回1，否则返回0
}

//获得栈顶元素的数据
char getTop(LinkStack *s){
    if (stackEmpty(s))
    {
        printf("栈为空，无法获取栈顶元素");
        exit(1);
    }
    return s->top->data;
}

//入栈
void push(LinkStack *s,char value){
    StackNode *newNode = (StackNode*)malloc(sizeof(StackNode));//创建新节点
    newNode->data = value;//赋值
    newNode->next = s->top;//使新节点指向原来的栈顶节点
    s->top = newNode;//更新栈顶指针位置
    s->count++;
}

//出栈
char pop(LinkStack *s){
    //判断链栈是否为空
    if (stackEmpty(s))
    {
        printf("栈为空！");
        exit(1);
    }
    LinkStackPtr temp = s->top;//定义一个指针指向栈顶节点
    char value = temp->data;//获取栈顶节点的数据
    s->top = temp->next;//更新栈顶指针位置
    free(temp);//释放节点内存
    return value;
}

//定义运算符优先级
int precedence(char operater){
    switch (operater)
    {
    case '+':
    case '-':
        return 1;
    case '*':
    case '/':
        return 2;
    case '(':
    case ')':
        return 0;
    default:
        return -1;
    }
}

//判断字符是否为数字字符
int isnumber(char str){
    return str >= '0' && str <= '9';
}


//递归比较运算符优先级
void compare(LinkStack *s,char currentStr,char *postfix,int *j){
    //栈为空，或栈顶运算符优先级大于等于当前运算符优先级
    if (stackEmpty(s) || getTop(s) == '(' || precedence(currentStr) > precedence(getTop(s)))
    {
        //将当前运算符入栈
        push(s,currentStr);
    }else {
        //否则将栈顶运算符弹出并添加到后缀表达式中
        postfix[(*j)++] = pop(s);
        //调用递归继续比较
        compare(s,currentStr,postfix,j);
    }
}


//将中缀表达式转换为后缀表达式
void infixTranslation(char *infix,char *postfix){
    //定义一个链栈来处理运算符
    LinkStack s;
    //初始化链栈
    initStack(&s);
    //代表后缀表达式的索引
    int j = 0;
    //遍历中缀表达式
    for (int i = 0; infix[i] != '\0'; i++)
    {
        //如果当前位置是空格，直接跳过
        if (infix[i] == ' ')
        {
            continue;
        }

        //判断是否为数字字符
        if (isnumber(infix[i]))
        {
            //是数字字符则直接添加到后缀表达式中
            postfix[j++] = infix[i];
        }else if (infix[i] == '(')
        {
            //是左括号则直接入栈
            push(&s,infix[i]);
        }else if (infix[i] == ')')
        {
            //是右括号则将栈顶的运算符出栈直到左括号
            while (!stackEmpty(&s) && getTop(&s)!= '(')
            {
               postfix[j++] = pop(&s);
            }
            //栈顶元素为左括号，弹出但不进入后缀表达式中
            if (!stackEmpty(&s) && getTop(&s) == '(')
                pop(&s);
        }else {
            //如果是运算符，调用递归函数处理
            compare(&s,infix[i],postfix,&j);
        }
    }
        
    //将栈剩余运算符弹出并添加到后缀表达式中
    while (!stackEmpty(&s))
    {
        postfix[j++] = pop(&s);
    }
    
    //给后缀表达式添加结束符
    postfix[j] = '\0';
    
}



//四则运算
double calculatorResult(double value1,double value2, char operator){
    switch (operator)
    {
    case '+':
        return value1 + value2;
        case '-':
        return value1 - value2;
    case '*':
        return value1 * value2;
    case '/':
        if (value2 == 0) {
            printf("错误：除数不能为零！\n");
            exit(1);
        }
        return value1 / value2;
    default:
        printf("错误：非法运算符！\n");
        exit(1);
    }
}

//后缀表达式求值
double calculatePostfix(char *postfix){
    LinkStack values;
    initStack(&values);

    for (int i = 0; postfix[i] != '\0'; i++)
    {
        if (isnumber(postfix[i]))
        {
            //当前字符为数字
            //将数字字符进行类型转换
            double num = postfix[i] - '0';
            push(&values,num);
        } else {
            //当前字符为运算符
            double value2 = pop(&values);
            double value1 = pop(&values);
            push(&values,calculatorResult(value1,value2,postfix[i]));
        }
    }
    return getTop(&values);
}

int main(){
    char infix[100];
    char postfix[100];
    printf("请输入一个四则运算表达式：");
    fgets(infix, sizeof(infix), stdin);
    infix[strcspn(infix, "\n")] = 0;  // 去除换行符

    infixTranslation(infix, postfix);
    printf("后缀表达式为：%s\n", postfix);

    double result = calculatePostfix(postfix);
    printf("表达式的值为：%.2f\n", result);

    return 0;
}