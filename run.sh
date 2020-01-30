echo "Compiling code ..."
if [ ! -d "/bin" ]; then
	mkdir bin
fi
javac -d bin -cp src src/Isola.java

echo "Running program..."
java -cp bin Isola