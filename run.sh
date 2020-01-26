echo "Compiling code ..."
if [ ! -d "/bin" ]; then
	mkdir bin
fi
javac -d bin -cp src src/Main.java -Xlint:deprecation

echo "Running program..."
java -cp bin Main