# makefile z_4

.SUFFIXES: .class .java

PACK=z_4
TARG=Test
JC="C:\Program Files\Java\jdk1.8.0_181\bin\javac.exe" -g
JM="C:\Program Files\Java\jdk1.8.0_181\bin\java.exe" -ea -jar
JR="C:\Program Files\Java\jdk1.8.0_181\bin\jar.exe" -cfe

OBJ=$(PACK)\VectorR3.class \
    $(PACK)\Test.class

.java.class:
	$(JC) $<

run: build
	$(JM) $(TARG).jar

build: 	$(OBJ)
	$(JR) $(TARG).jar $(PACK).Test $(OBJ)

clean:
	for %%f in ($(OBJ)) do del %%f
	del $(TARG).jar

$(PACK)\VectorR3.class: $(PACK)\VectorR3.java makefile

$(PACK)\Test.class: $(PACK)\Test.java makefile

# eof makefile z_4
