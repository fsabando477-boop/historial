# Historial de acciones - Semana 7

Aplicacion en Java que implementa una **pila (LIFO) generica a mano** y la usa
como respaldo del patron **Repository** para gestionar el historial de acciones
realizadas sobre un catalogo (agregar, editar, eliminar) con soporte de
`deshacer`.

## Problema seleccionado

Registrar cronologicamente las acciones que el usuario realiza sobre un
catalogo y permitir revertirlas en orden inverso al que fueron aplicadas. Es el
caso de uso clasico de una **pila (LIFO)**: la ultima accion realizada es la
primera que se debe deshacer.

## Conceptos aplicados

- **Tipo de dato abstracto lineal**: `com.fsabando.historial.estructura.Pila<T>`
  implementada a mano con una lista enlazada de nodos privados. No se usa
  `java.util.Stack` ni `Deque`. Todas las operaciones fundamentales son O(1):
  - `apilar(T)` -> agregar.
  - `desapilar()` -> eliminar el tope.
  - `cima()` -> consultar el siguiente elemento.
  - `estaVacia()` -> verificar si esta vacia.
  - `cantidad()` -> cantidad de elementos.
  - `vaciar()` -> auxiliar para pruebas y para "limpiar historial".
- **Patron Repository**: la interfaz
  `com.fsabando.historial.repositorio.HistorialRepository` define el contrato
  (registrar, deshacer, ultimaAccion, hayHistorial, cantidad, limpiar). La
  clase `HistorialRepositoryPila` la implementa delegando en la pila. La
  aplicacion depende de la interfaz, no de la pila, asi que manana se puede
  sustituir por una implementacion respaldada en base de datos sin tocar el
  codigo cliente.
- **Pruebas unitarias con JUnit 5**: 18 casos que cubren cada operacion de la
  pila y del repositorio, incluidos los casos de error (`desapilar` en pila
  vacia, `registrar(null)`, etc.).

## Estructura del codigo

```
com.fsabando.historial
 |- Main.java                           (demo por consola)
 |- modelo/Accion.java                  (dominio: tipo + descripcion + momento)
 |- estructura/Pila.java                (pila LIFO generica manual)
 `- repositorio/
     |- HistorialRepository.java        (interfaz del patron Repository)
     `- HistorialRepositoryPila.java    (impl respaldada por Pila<Accion>)
```

## Requisitos

- JDK 17 o superior.
- Maven 3.8+ (opcional; tambien se puede compilar con `javac`).

## Ejecutar la demo

Con Maven:

```
mvn -q compile exec:java
```

Sin Maven:

```
mkdir -p target/classes
javac -d target/classes --release 17 $(find src/main/java -name "*.java")
java -cp target/classes com.fsabando.historial.Main
```

## Ejecutar las pruebas unitarias

Con Maven (recomendado):

```
mvn test
```

Desde IntelliJ IDEA: abrir el proyecto (`File -> Open` sobre `pom.xml`),
esperar a que descargue las dependencias y hacer *right-click -> Run 'Tests
in historial'* sobre la carpeta `src/test/java`.

Sin Maven, con el jar standalone de JUnit 5:

```
mkdir -p target/test-classes
javac -d target/test-classes -cp "target/classes;junit-platform-console-standalone-1.12.2.jar" \
      --release 17 $(find src/test/java -name "*.java")
java -jar junit-platform-console-standalone-1.12.2.jar execute \
      --class-path "target/classes;target/test-classes" --scan-class-path
```
