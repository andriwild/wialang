JC = javac
JFLAGS = -d bin

SRC = $(shell find src -wholename "src/wialang/*.java")
BIN = bin

MAIN = wialang.Jwia

EXEC = wia

all: $(EXEC)

compile:
	@echo "Compile Java sources..."
	mkdir -p $(BIN)
	$(JC) $(JFLAGS) $(SRC)

$(EXEC): compile
	@echo "Create script '$(EXEC)'..."
	@echo '#!/bin/bash' > $(EXEC)
	@echo 'java -cp $(BIN) $(MAIN) "$$@"' >> $(EXEC)
	@chmod +x $(EXEC)

run: $(EXEC)
	./$(EXEC)

clean:
	@echo "Clean up..."
	rm -rf $(BIN)/*
	rm -f $(EXEC)
