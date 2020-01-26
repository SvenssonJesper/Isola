echo "Compiling code ..."
if [ ! -d "/bin" ]; then
	mkdir bin
	echo hej
fi
javac -d bin -cp src src/main.java -Xlint:deprecation

echo "Running program..."
java -cp bin main