all: clean
	@mkdir -p ./bin
	@cd ./src/Compiler2015/Parser && java -jar "../../../lib/antlr-4.5-complete.jar" -package Compiler2015.Parser -Dlanguage=Java -listener -no-visitor -lib . Compiler2015.g4
	@cd ./src && javac -cp "../lib/antlr-4.5-complete.jar" \
		./Compiler2015/*/*/*/*/*.java \
		./Compiler2015/*/*/*/*.java \
		./Compiler2015/*/*/*.java \
		./Compiler2015/*/*.java \
		./Compiler2015/*.java \
		-d ../bin
	@cp ./lib/antlr-4.5-complete.jar ./bin
	@cp ./lib/StdLib.c ./bin
	@cd ./bin && jar xf ./antlr-4.5-complete.jar \
			 && rm -rf ./META-INF \
			 && jar cef Compiler2015/Main Compiler2015.jar ./ \
			 && rm -rf ./antlr-4.5-complete.jar ./Compiler2015  ./org  ./st4hidden

clean:
	rm -rf ./bin
