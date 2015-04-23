
//key 0
//id 1
//int_const 2
//char_const 3
//string_const 4
//op 5

char *src_code = 0, *src_tail = 0, *cur_scan = 0;
int src_buffer_len, col = 1, row = 1;

struct Array {
	int num, mul;
	struct Array *nxt;
};

struct Array *Array_() {
	struct Array *res = (struct Array *) malloc(sizeof (struct Array));
	res->num = 0;
	res->mul = 0;
	res->nxt = 0;
	return res;
}

struct Variable {
	char *id;
	int level;
	int from;
	struct Type *type;
	struct Variable *nxt, *args;
};

struct Variable *Variable_(char *id) {
	struct Variable *res = (struct Variable *) malloc(sizeof (struct Variable));
	res->id = id;
	res->level = 0;
	res->type = 0;
	res->from = 0;
	res->nxt = 0;
	res->args = 0;
	return res;
}

struct Type {
	int is_struct;
	int width;
	char *_literal;
	struct Type *nxt;
	struct Variable *mem;
} *type_int, *type_char, *type_void;

struct Type *Type_() {
	struct Type *res = (struct Type *) malloc(sizeof (struct Type));
	res->is_struct = 0;
	res->mem = 0;
	res->width = -1; //The symbol of empty Type
	res->_literal = ""; //Anon with empty string, prevent re
	res->nxt = 0;
	return res;
}

struct Environment {
	struct Environment *pre;
	struct Type *types;
	struct Variable *vars;
	int env; //global, struct or union, function, for || while, if
	int dummy_cnt;
} *global, *cur_env;

struct Environment *Environment_(struct Environment *pre, int env){
	struct Environment *res = (struct Environment *) malloc(sizeof (struct Environment));
	res->types = 0;
	res->vars = 0;
	res->env = env;
	res->dummy_cnt = 0;
	if (pre) {
		res->pre = pre;
	} else {
		res->pre = 0;
	}
	return res;
};

struct Function {
	char *id;
	struct Variable *args;
	struct Funcion *nxt;
};

struct Token {
	int type;
	int col, row;
	char *_literal;
	union {
		int int_val;
		int char_val;
		char *str_val;
	};
} look;

struct Expression {
	struct Type *type;
	struct Variable *ref;
	char *op;
	struct Expression *l, *r;
	int dummy_num;
	int is_const, const_val;
	int is_left;
};

struct Expression *Expression_() {
	struct Expression *res = (struct Expression *) malloc(sizeof (struct Expression));
	res->type = 0;
	res->ref = 0;
	res->op = "";
	res->l = res->r = 0;
	res->dummy_num = 0;
	res->is_const = 0;
	res->const_val = -1;
	return res;
}

struct Expression *make_binary_dummy(char *op, struct Expression *l, struct Expression *r) {
	struct Expression *res = Expression_();
	res->op = op;
	res->dummy_num = ++cur_env->dummy_cnt;
	res->l = l;
	res->r = r;
	return res;
}

struct Expression *make_unary_dummy(char *op, struct Expression *r) {
	struct Expression *res = Expression_();
	res->op = op;
	res->r = r;
	res->dummy_num = ++cur_env->dummy_cnt;
	return res;
}

struct Expression *make_postfix_dummy(char *op, struct Expression *l) {
	struct Expression *res = Expression_();
	res->op = op;
	res->l = l;
	res->dummy_num = ++cur_env->dummy_cnt;
	return res;
}

int is_basic_type(struct Type *);
int my_strcmp(char *, char *);
int str_start_with(char *, char *);
void eat_chars(int);
void eat_useless_chars();
char analy_and_jump(char **);
struct Token next_token();
void read_src();
struct Variable *parse_plain_declarator();
void parse_array();
struct Variable *parse_declarator();
struct Variable *parse_declarators();
struct Type *parse_type_specifier();
void parse_parameters();
void parse_initializer();
void parse_init_declarators();
void parse_compound_stmt();
void parse_stmt();
void parse_program();
struct Expression *parse_expr();
struct Expression *parse_assign_expr();
struct Expression *parse_const_expr();
struct Expression *parse_logic_or_expr();
struct Expression *parse_logic_and_expr();
struct Expression *parse_or_expr();
struct Expression *parse_xor_expr();
struct Expression *parse_and_expr();
struct Expression *parse_equality_expr();
struct Expression *parse_relational_expr();
struct Expression *parse_shift_expr();
struct Expression *parse_additive_expr();
struct Expression *parse_multi_expr();
struct Expression *parse_cast_expr();
struct Expression *parse_unary_expr();
struct Expression *parse_postfix_expr();
struct Expression *parse_primary_expr();
struct Expression *parse_arguments();
int is_type();
int unary_operator();
void emit_type_specifier(struct Type*);

void emit_type_specifier(struct Type *this) {
	if (!is_basic_type(this)) {
		if (this->is_struct) {
			printf("struct");
		} else {
			printf("union");
		}
		printf(" %s", this->_literal);
		printf(" {\n");
		struct Variable *mem = this->mem;
		while (mem) {
			printf("\t%s %s %d\n", mem->type->_literal, mem->id, mem->from);
			mem = mem->nxt;
		}
		printf("}\n");
	} else {
		printf("%s\n", this->_literal);
	}
}

int is_basic_type(struct Type *this) {
	return this == type_char || this == type_void || this == type_int;
}

struct Expression *parse_const_expr() {
	parse_assign_expr();
	return 0;
}

struct Expression *parse_assign_expr() {
	struct Expression *l = parse_logic_or_expr(), *tmp;
	//char *cur = look._literal;
	char *op;
	while (look._literal[0] == '=' || look._literal[1] == '=' || look._literal[2] == '=') {
		op = look._literal;
		look = next_token();
		tmp = make_binary_dummy(op, l, parse_assign_expr());
		tmp->is_const = (tmp->l->is_const && tmp->r->is_const) || (tmp->r->is_const && *op == '=');
		if (tmp->is_const) {
			if (*op == '=') {
				tmp->const_val = tmp->r->const_val;
			} else if (*op == '*') {
				tmp->const_val = tmp->l->const_val * tmp->r->const_val;
			} else if (*op == '/') {
				tmp->const_val = tmp->l->const_val / tmp->r->const_val;
			} else if (*op == '%') {
				tmp->const_val = tmp->l->const_val % tmp->r->const_val;
			} else if (*op == '+') {
				tmp->const_val = tmp->l->const_val + tmp->r->const_val;
			} else if (*op == '-') {
				tmp->const_val = tmp->l->const_val - tmp->r->const_val;
			} else if (*op == '<') {
				tmp->const_val = tmp->l->const_val << tmp->r->const_val;
			} else if (*op == '>'){
				tmp->const_val = tmp->l->const_val >> tmp->r->const_val;
			} else if (*op == '&') {
				tmp->const_val = tmp->l->const_val & tmp->r->const_val;
			} else if (*op == '^') {
				tmp->const_val = tmp->l->const_val ^ tmp->r->const_val;
			} else if (*op == "|") {
				tmp->const_val = tmp->l->const_val | tmp->r->const_val;
			}
		}
		l = tmp;
	}
	return l;
}

struct Expression *parse_logic_or_expr() {
	struct Expression *l = parse_logic_and_expr(), *tmp;
	while (!my_strcmp(look._literal, "||")) {
		look = next_token();
		parse_logic_and_expr();
	}
}

struct Expression *parse_logic_and_expr() {
	parse_and_expr();
	while (!my_strcmp(look._literal, "&&")) {
		look = next_token();
		parse_and_expr();
	}
}

struct Expression *parse_and_expr() {
	parse_xor_expr();
	while (!my_strcmp(look._literal, "&")) {
		look = next_token();
		parse_xor_expr();
	}
}

struct Expression *parse_xor_expr() {
	parse_or_expr();
	while (!my_strcmp(look._literal, "^")) {
		look = next_token();
		parse_or_expr();
	}
}

struct Expression *parse_or_expr() {
	parse_equality_expr();
	while (!my_strcmp(look._literal, "|")) {
		look = next_token();
		parse_equality_expr();
	}
}

struct Expression *parse_equality_expr() {
	parse_relational_expr();
	while (!my_strcmp(look._literal, "==") || !my_strcmp(look._literal, "!=")) {
		look = next_token();
		parse_relational_expr();
	}
}

struct Expression *parse_relational_expr() {
	parse_shift_expr();
	while (!my_strcmp(look._literal, "<") || !my_strcmp(look._literal, ">") || !my_strcmp(look._literal, ">=") || !my_strcmp(look._literal, "<=")) {
		look = next_token();
		parse_shift_expr();
	}
}

struct Expression *parse_shift_expr() {
	parse_additive_expr();
	while (!my_strcmp(look._literal, "<<") || !my_strcmp(look._literal, ">>")) {
		look = next_token();
		parse_additive_expr();
	}
}

struct Expression *parse_additive_expr() {
	parse_multi_expr();
	while (!my_strcmp(look._literal, "+") || !my_strcmp(look._literal, "-")) {
		look = next_token();
		parse_multi_expr();
	}
}

struct Expression *parse_multi_expr() {
	parse_cast_expr();
	while (!my_strcmp(look._literal, "*") || !my_strcmp(look._literal, "/") || !my_strcmp(look._literal, "%")) {
		look = next_token();
		parse_cast_expr();
	}
}

int is_type() {
	return !my_strcmp(look._literal, "int") || !my_strcmp(look._literal, "char") || !my_strcmp(look._literal, "void") || !my_strcmp(look._literal, "struct") || !my_strcmp(look._literal, "union");
}

struct Expression *parse_cast_expr() {
	if (!my_strcmp(look._literal, "(")) {
		look = next_token();
		if (is_type()) {
			parse_type_specifier();
			while (!my_strcmp(look._literal, "*")) {
				look = next_token();
			}
			look = next_token();
			parse_cast_expr();
		} else {
		//This is primary...
			parse_expr();
			look = next_token();
			for (;;) {
				if (!my_strcmp(look._literal, "(")) {
					look = next_token();
					parse_arguments();
					look = next_token();
				} else if (!my_strcmp(look._literal, "[")) {
					look = next_token();
					parse_expr();
					look = next_token();
				} else if (!my_strcmp(look._literal, ".")) {
					look = next_token();
					look = next_token();
				} else if (!my_strcmp(look._literal, "->")) {
					look = next_token();
					look = next_token();
				} else if (!my_strcmp(look._literal, "++") || !my_strcmp(look._literal, "--")) {
					look = next_token();
				} else {
					break;
				}
			}
		}
	} else {
		parse_unary_expr();
	}
}

struct Expression *parse_unary_expr() {
	if (!my_strcmp(look._literal, "++") || !my_strcmp(look._literal, "--")) {
		look = next_token();
		parse_unary_expr();
	} else if (!my_strcmp(look._literal, "sizeof")) {
		look = next_token();
		if (!my_strcmp(look._literal, "(")) {
			look = next_token();
			parse_type_specifier();
			while (!my_strcmp(look._literal, "*")) {
				look = next_token();
			}
			look = next_token();
		} else {
			parse_unary_expr();
		}
	} else if (!my_strcmp(look._literal, "&")) {
		look = next_token();
		parse_cast_expr();
	} else if (!my_strcmp(look._literal, "*")) {
		look = next_token();
		parse_cast_expr();
	} else if (!my_strcmp(look._literal, "+")) {
		look = next_token();
		parse_cast_expr();
	} else if (!my_strcmp(look._literal, "-")) {
		look = next_token();
		parse_cast_expr();
	} else if (!my_strcmp(look._literal, "~")) {
		look = next_token();
		parse_cast_expr();
	} else if (!my_strcmp(look._literal, "!")) {
		look = next_token();
		parse_cast_expr();
	} else {
		parse_postfix_expr();
	}
}

struct Expression *parse_arguments() {
	while (my_strcmp(look._literal, ")")) {
		parse_assign_expr();
		if (!my_strcmp(look._literal, ",")) {
			look = next_token();
		}
	}
}

struct Expression *parse_postfix_expr() {
	parse_primary_expr();
//This is postfixes
	for (;;) {
		if (!my_strcmp(look._literal, "(")) {
			look = next_token();
			parse_arguments();
			look = next_token();
		} else if (!my_strcmp(look._literal, "[")) {
			look = next_token();
			parse_expr();
			look = next_token();
		} else if (!my_strcmp(look._literal, ".")) {
			look = next_token();
			look = next_token();
		} else if (!my_strcmp(look._literal, "->")) {
			look = next_token();
			look = next_token();
		} else if (!my_strcmp(look._literal, "++") || !my_strcmp(look._literal, "--")) {
			look = next_token();
		} else {
			break;
		}
	}
}

struct Expression *parse_primary_expr() {
	if (look.type >= 1 && look.type <= 4) {
		look = next_token();
	} else if (!my_strcmp(look._literal, "(")) {
		look = next_token();
		parse_expr();
		look = next_token();
	} else {
	}
}

struct Expression *parse_expr() {
	struct Expression *l = parse_assign_expr(), *tmp;
	while (!my_strcmp(look._literal, ",")) {
		look = next_token();
		tmp = make_binary_dummy(",", l, parse_assign_expr());
		tmp->is_const = tmp->r->is_const;
		if (tmp->is_const) {
			tmp->const_val = tmp->r->const_val;
		}
		l = tmp;
	}
	return l;
}

int my_strcmp(char *s1, char *s2) {
	while (*s1 && *s2 && *s1 == *s2) {
		++s1, ++s2;
	}
	return *s1 - *s2;
}

int str_start_with(char *s1, char *s2) {
	while (*s1 && *s2) {
		if (*s1++ != *s2++) {
			return 0;
		}
	}
	return 1;
}

void eat_chars(int num) {
	while (num--) {
		if (*cur_scan == '\n') {
			++row;
			col = 0;
		}
		if (*cur_scan == '\t') {
			if (col % 4) {
				col += 4 - col % 4;
			} else {
				col += 8;
			}
		} else {
			++col;
		}
		++cur_scan;
	}
}

void eat_useless_chars() {
	char *before = 0;
	while (before != cur_scan) {
		before = cur_scan;
		while (cur_scan < src_tail && (*cur_scan == '\n' || *cur_scan == '\t' || *cur_scan == ' ')) {
			eat_chars(1);
		}
		if (cur_scan < src_tail && *cur_scan == '#') {
			while (cur_scan < src_tail && *cur_scan != '\n') {
				eat_chars(1);
			}
			eat_chars(1);
		}
		if (cur_scan < src_tail && *cur_scan == '/' && cur_scan[1] == '/') {
			while (cur_scan < src_tail && *cur_scan != '\n') {
				eat_chars(1);
			}
			eat_chars(1);
		}
		if (cur_scan < src_tail && *cur_scan == '/' && cur_scan[1] == '*') {
			while ((*cur_scan != '*' || cur_scan[1] != '/')) {
				eat_chars(1);
			}
			eat_chars(2);
		}
	}
}

char analy_and_jump(char **s) {
	if (**s != '\\') {
		(*s) += 1;
		return **s;
	} else if ((*s)[1] == 'a') {
		(*s) += 2;
		return '\a';
	} else if ((*s)[1] == 'b') {
		(*s) += 2;
		return '\b';
	} else if ((*s)[1] == 'f') {
		(*s) += 2;
		return '\f';
	} else if ((*s)[1] == 'n') {
		(*s) += 2;
		return '\n';
	} else if ((*s)[1] == 't') {
		(*s) += 2;
		return '\t';
	} else if ((*s)[1] == 'v') {
		(*s) += 2;
		return '\v';
	} else if ((*s)[1] == 'r') {
		(*s) += 2;
		return '\r';
	} else if ((*s)[1] == '\"') {
		(*s) += 2;
		return '\"';
	} else if ((*s)[1] == '\'') {
		(*s) += 2;
		return '\'';
	} else if ((*s)[1] == '\'') {
		(*s) += 2;
		return '\'';
	} else if ((*s)[1] == '\\') {
		(*s) += 2;
		return '\\';
	} else if ((*s)[1] == 'X' || (*s)[1] == 'x') {
		char *tail = *s + 2;
		char val = 0;
		while(*tail != '\0' && ((*tail >= '0' && *tail <= '9') || (*tail >= 'A' && *tail <= 'F'))) {
			val = val * 16;
			if (*tail >= '0' && *tail <= '9') {
				val += *tail - '0';
			} else {
				val += *tail - 'A' + 10;
			}
			++tail;
		}
		*s = tail;
		return val;
	} else {
		char *tail = *s + 1;
		char val = 0;
		while(*tail >= '0' && *tail <= '7') {
			val = val * 8 + *tail - '0';
			++tail;
		}
		*s = tail;
		return val;
	}
	return

			0;
}

struct Token next_token() {
	int ans = 0;
	struct Token res;
	eat_useless_chars();
	res.row = row;
	res.col = col;
	if (str_start_with(cur_scan, "void") && 4 > ans) { //KEY
		ans = 4;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "char") && 4 > ans) {
		ans = 4;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "int") && 3 > ans) {
		ans = 3;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "typedef") && 7 > ans) {
		ans = 7;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "for") && 3 > ans) {
		ans = 3;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "continue") && 8 > ans) {
		ans = 8;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "struct") && 6 > ans) {
		ans = 6;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "return") && 6 > ans) {
		ans = 6;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "sizeof") && 6 > ans) {
		ans = 6;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "union") && 5 > ans) {
		ans = 5;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "while") && 5 > ans) {
		ans = 5;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "if") && 2 > ans) {
		ans = 2;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "else") && 4 > ans) {
		ans = 4;
		res.type = 0;
	}
	if (str_start_with(cur_scan, "break") && 5 > ans) {
		ans = 5;
		res.type = 0;
	}
	if ((*cur_scan >= 'a' && *cur_scan <= 'z') || (*cur_scan >= 'A' && *cur_scan <= 'Z') || *cur_scan == '_') { //IDENTIFIER
		char *tail = cur_scan + 1;
		while (tail < src_tail && ((*tail >= 'a' && *tail <= 'z') || (*tail >= 'A' && *tail <='Z') || *tail == '_' || (*tail >= '0' && *tail <= '9'))) {
			++tail;
		}
		if (tail - cur_scan > ans) { //IDENTIFIER
			ans = tail - cur_scan;
			res.type = 1;//id
		}
	}
	if (*cur_scan >= '0' && *cur_scan <= '9') { //INT_CONST
		int val = *cur_scan - '0';
		char *tail = cur_scan + 1;
		while (tail < src_tail && (*tail >= '0' && *tail <= '9')) {
			val = val * 10 + *tail - '0';
			++tail;
		}
		if (tail - cur_scan > ans) {
			ans = tail - cur_scan;
			res.type = 2;
			res.int_val = val;
		}
	}
	if (str_start_with(cur_scan, "0x") || str_start_with(cur_scan, "0X")) { //INT_CONST heximal
		int val = cur_scan[2] - '0';
		char *tail = cur_scan + 3;
		while (tail < src_tail && ((*tail >= '0' && *tail <= '9') || (*tail >= 'A' && *tail <= 'F'))) {
			val = val * 16;
			if (*tail >= '0' && *tail <= '9') {
				val += *tail - '0';
			} else {
				val += *tail - 'A' + 10;
			}
			++tail;
		}
		if (tail - cur_scan > ans) {
			ans = tail - cur_scan;
			res.type = 2;
			res.int_val = val;
		}
	}
	if (*cur_scan == '\'') { //CHAR_CONST
		char *tail = cur_scan + 1;
		char val = analy_and_jump(&tail);
		++tail;
		if (tail - cur_scan > ans) {
			ans = tail - cur_scan;
			res.type = 3;
			res.char_val = val;
		}
	}
	if (*cur_scan == '\"') { //STRING_CONST
		char *tail = cur_scan + 1;
		while (tail < src_tail && (*tail != '\"' || tail[-1] == '\\')) {
			++tail;
		}
		if (tail + 1 - cur_scan > ans) {
			ans = tail + 1 - cur_scan;
			res.type = 4;
			char *tmp = res.str_val = (char *) malloc(tail + 5 - cur_scan + 1);
			tail = cur_scan + 1;
			while (tail < src_tail && (*tail != '\"' || tail[-1] == '\\')) {
				*tmp++ = analy_and_jump(&tail);
			}
			*tmp = 0;
		}
	}
	if (*cur_scan == '(' && 1 > ans) { //OP
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == ')' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == ';' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == ',' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '=' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '{' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '}' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '[' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == ']' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '*' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '|' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '^' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '&' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '<' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '>' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '+' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '-' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '/' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '%' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '~' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '!' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (*cur_scan == '.' && 1 > ans) {
		ans = 1;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "||") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "&&") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "==") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "!=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "<=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, ">=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "<<") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, ">>") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "++") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "--") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "->") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "+=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "-=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "*=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "/=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "%=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "|=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "^=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "|=") && 2 > ans) {
		ans = 2;
		res.type = 5;
	}
	if (str_start_with(cur_scan, ">>=") && 3 > ans) {
		ans = 3;
		res.type = 5;
	}
	if (str_start_with(cur_scan, "<<=") && 3 > ans) {
		ans = 3;
		res.type = 5;
	}
	char *j = res._literal = (char *) malloc(ans + 1), *i;
	for (i = cur_scan; i - cur_scan < ans; ) {
		*j++ = *i++;
	}
	//fprintf(stderr, "<%d, %d> %s\n", res.row, res.col, res._literal);
	*j = 0;
	eat_chars(ans);
	eat_useless_chars();
	return res;
}

struct Variable *parse_plain_declarator() {
	int lv = 0;
	while (!my_strcmp(look._literal, "*")) {
		look = next_token();
		++lv;
	}
	struct Variable *res = Variable_(look._literal);
	res->level = lv;
	look = next_token();
	return res;
}

void parse_array() {
	if (!my_strcmp(look._literal, "[")) {
		while (!my_strcmp(look._literal, "[")) {
			look = next_token();
			if (my_strcmp(look._literal, "]")) {
				parse_const_expr();
			}
			look = next_token();
		}
	}
}

struct Variable *parse_declarator() {
	struct Variable *res = parse_plain_declarator();
	parse_array();
	parse_parameters();
	return res;
}

struct Variable *parse_declarators() {
	struct Variable *res, *tmp;
	for (res = parse_declarator(); !my_strcmp(look._literal, ","); ) {
		look = next_token();
		tmp = parse_declarator();
		tmp->nxt = res;
		res = tmp;
	}
	return res;
}

struct Type *parse_type_specifier() {
	if (!my_strcmp(look._literal, "int")) {
		look = next_token();
		return type_int;
	} else if (!my_strcmp(look._literal, "char")) {
		look = next_token();
		return type_char;
	} else if (!my_strcmp(look._literal, "void")) {
		look = next_token();
		return type_void;
	} else if (!my_strcmp(look._literal, "struct") || !my_strcmp(look._literal, "union")) {
		int is_struct = !my_strcmp(look._literal, "struct");
		char *id = "";
		look = next_token();
		struct Type *res = 0;
		int same = 1;
		if (look.type == 1 || !my_strcmp(look._literal, "{")) {
			if (look.type == 1) {
				//puts(look._literal);
				id = look._literal;
				look = next_token();
				struct Environment *env;
				struct Type *types;
				for (env = cur_env; env; env = env->pre, same = 0) {
					for (types = env->types; types; types = types->nxt) {
						if (!my_strcmp(types->_literal, id)) {
							res = types;
							break;
						}
					}
				}
			}
			if (!res || (!same && res->width == -1)) {
				/*Only the struct or union in the same scope can be overide declaration*/
				res = Type_();
				res->_literal = id;
				res->is_struct = is_struct;
				res->nxt = cur_env->types;
				cur_env->types = res;
			}
			if (!my_strcmp(look._literal, "{")) {
				look = next_token();
				res->width = 0;
				while (my_strcmp(look._literal, "}")) {
					struct Environment *tmp = Environment_(cur_env, 1);
					cur_env = Environment_(cur_env, 1);
					struct Type *sub_type = parse_type_specifier();
					cur_env = cur_env->pre;
					if (my_strcmp(look._literal, ";")) {
						struct Variable *mem = parse_declarators(), *tmp;
						while (mem) {
							tmp = mem->nxt;
							mem->type = sub_type;
							if (is_struct) {
								mem->from = res->width;
								res->width += sub_type->width;
							} else {
								mem->from = 0;
							}
							mem->nxt = res->mem;
							res->mem = mem;
							mem = tmp;
						}
						if (!is_struct && sub_type->width > res->width) {
							res->width = sub_type->width;
						}
					} else {
						if (!my_strcmp(sub_type->_literal, "")) {
							//puts("anonymous gadget inside!");
							if (sub_type->is_struct) {
								puts("namae no na i struct");
							} else {
								puts("namae no na i union");
							}
							struct Variable *mem = sub_type->mem, *tmp = 0;
							while (mem) {
								tmp = mem->nxt;
								if (is_struct) {
									mem->from += res->width;
								}
								mem->nxt = res->mem;
								res->mem = mem;
								mem = tmp;
							}
							if (res->is_struct) {
								res->width += sub_type->width;
							} else {
								if (sub_type->width > res->width)
									res->width = sub_type->width;
							}
						} else {
							sub_type->nxt = cur_env->types;
							cur_env->types = sub_type;
						}
					}
					look = next_token();
				}
				look = next_token();
				return res;
			} else {
				puts("Declaration or pre-declaration...");
				return res;
			}
		}
	} else {
	}
	return 0;
}

void parse_parameters() {
	if (!my_strcmp(look._literal, "(")) {
		for (look = next_token(); my_strcmp(look._literal, ")");) {
			parse_type_specifier();
			if (!my_strcmp(look._literal, "*") || look.type == 1) {
				//parse_declarator();
				while (!my_strcmp(look._literal, "*")) {
					look = next_token();
				}
				if (look.type == 1) {
					look = next_token();
					parse_array();
				}
			}
			if (!my_strcmp(look._literal, ",")) {
				look = next_token();
			}
		}
		look = next_token();
	}
}

void parse_initializer() {
	if (!my_strcmp(look._literal, "{")) {
		int not_closed = 1, pre_expr = 0;
		look = next_token();
		while (not_closed) {
			if (!my_strcmp(look._literal, "}")) {
				look = next_token();
				//pre_expr = 0;
				--not_closed;
				continue;
			} else if (!my_strcmp(look._literal, "{")) {
				look = next_token();
				pre_expr = 0;
				++not_closed;
				continue;
			} else if (!my_strcmp(look._literal, ",")) {
				look = next_token();
				pre_expr = 0;
			} else {
				parse_assign_expr();
				pre_expr = 1;
			}
		}
	} else {
		parse_assign_expr();
	}
}

void parse_init_declarators() {
	for (parse_declarator(); ; parse_declarator()) {
		if (!my_strcmp(look._literal, "=")) {
			look = next_token();
			parse_initializer();
		}
		if (my_strcmp(look._literal, ",")) {
			break;
		} else {
			look = next_token();
		}
	}
}

void parse_compound_stmt() {
	look = next_token();
	while (my_strcmp(look._literal, "}")) {
		if (!my_strcmp(look._literal, "struct") || !my_strcmp(look._literal, "union") || !my_strcmp(look._literal, "char") || !my_strcmp(look._literal, "int") || !my_strcmp(look._literal, "void")) {
			parse_type_specifier();
			parse_init_declarators();
			look = next_token();
		} else {
			parse_stmt();
		}
	}
	look = next_token();
}

void parse_stmt() {
	if (!my_strcmp(look._literal, ";")) {
		look = next_token();
	} else if (!my_strcmp(look._literal, "{")) {
		parse_compound_stmt();
	} else if (!my_strcmp(look._literal, "if")){
		look = next_token();
		look = next_token();
		parse_expr();
		look = next_token();
		parse_stmt();
		if (!my_strcmp(look._literal, "else")) {
			look = next_token();
			parse_stmt();
		}
	} else if (!my_strcmp(look._literal, "for")) {
		look = next_token();
		look = next_token();
		if (my_strcmp(look._literal, ";")) {
			parse_expr();
		}
		look = next_token();
		if (my_strcmp(look._literal, ";")) {
			parse_expr();
		}
		look = next_token();
		if (my_strcmp(look._literal, ")")) {
			parse_expr();
		}
		look = next_token();
		parse_stmt();
	} else if (!my_strcmp(look._literal, "while")) {
		look = next_token();
		look = next_token();
		parse_expr();
		look = next_token();
		parse_stmt();
	} else if (!my_strcmp(look._literal, "continue")) {
		look = next_token();
		look = next_token();
	} else if (!my_strcmp(look._literal, "break")) {
		look = next_token();
		look = next_token();
	} else if (!my_strcmp(look._literal, "return")) {
		look = next_token();
		if (!my_strcmp(look._literal, ";")) {
			look = next_token();
		} else {
			parse_expr();
			look = next_token();
		}
	} else {
		parse_expr();
		look = next_token();
	}
}

void parse_program() {
	look = next_token();
	while (cur_scan < src_tail) {
		if (my_strcmp(look._literal, "typedef")) {
			cur_env = Environment_(cur_env, 1);
			struct Type *this = parse_type_specifier();
			emit_type_specifier(this);
			cur_env = cur_env->pre;
			if (!my_strcmp(look._literal, ";")) {
				this->nxt = cur_env->types;
				cur_env->types = this;
				look = next_token();
				continue;
			}
			struct Variable *head = parse_declarator();
			head->type = this;
			if (!my_strcmp(look._literal, "{")) {
				parse_compound_stmt();
			} else {
				if (!my_strcmp(look._literal, ";")) {
					head->nxt = cur_env->vars;
					cur_env = head;
					look = next_token();
					continue;
				} else if (!my_strcmp(look._literal, ",")) {
					look = next_token();
				} else {
					parse_array();
					if (!my_strcmp(look._literal, "=")) {
						look = next_token();
						parse_initializer();
					}
					if (!my_strcmp(look._literal, ",")) {
						look = next_token();
					} else if (!my_strcmp(look._literal, ";")) {
						look = next_token();
						continue;
					} else {
					}
				}
				parse_init_declarators();
				look = next_token();
			}
		} else {
		}
	}
}

void read_src() {
	char ch;
	while ((ch = getchar()) != -1) {
		if (src_tail - src_code + 5 >= src_buffer_len) {
			src_buffer_len = src_buffer_len * 2 + 10;
			char *tmp = (char *) malloc(src_buffer_len);
			int i;
			for (i = 0; i < src_tail - src_code; ++i) {
				tmp[i] = src_code[i];
			}
			src_code = tmp;
			src_tail = tmp + i;
		}
		*src_tail++ = ch;
		*src_tail = 0;
	}
}

void initialize() {
	read_src();
	cur_scan = src_code;

	cur_env = global = Environment_(0, 0);

	type_int = Type_();
	type_int->_literal = "int";
	type_int->width = 4;
	type_char = Type_();
	type_char->_literal = "char";
	type_char->width = 1;
	type_void = Type_();
	type_void->width = 1;

	type_char->nxt = type_int;
	type_int->nxt = type_void;
	type_void->nxt = global->types;
}

int main(int argv, char *args[]) {
	initialize();
	parse_program();
	return 0;
}