int printf(char *format, ...) {
	va_list ap;
	char *prev = format;
	int arg, tmp, len;
	va_start(ap, format);
	for ( ; *format; ++format) {
		if (*format == '%') {
			*format = 0;
			___yzgysjr_lib_putstring(prev);
			*format = '%';
			++format;

			if (*format == 'd')
				___yzgysjr_lib_putint(va_arg(ap, int));
			else if (*format == 'c')
				putchar(va_arg(ap, char));
			else if (*format == 's')
				___yzgysjr_lib_putstring(va_arg(ap, char *));
			else {
				len = *(++format)-'0';
				++format;
				arg = va_arg(ap, int);
				if (arg < 0) {
					arg = -arg;
					--len;
					putchar('-');
				}
				for (tmp = arg; tmp; tmp /= 10)
					--len;
				for (; len > 0; --len)
					putchar('0');
				if (arg)
					___yzgysjr_lib_putint(arg);
			}
			prev = format + 1;
		}
	}
	___yzgysjr_lib_putstring(prev);
}
