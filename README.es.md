<p align="center">
    <img src="./docs/img/AlgoStar_banner_space_bg.png" alt="AlgoStar Banner">
</p>

<div align="center">

![tp2](https://github.com/walgab/AlgoStar/actions/workflows/build.yml/badge.svg) [![codecov](https://codecov.io/gh/walgab/AlgoStar/branch/master/graph/badge.svg)](https://codecov.io/gh/walgab/AlgoStar) [![en](https://img.shields.io/badge/lang-en-blue.svg)](https://github.com/walgab/AlgoStar#readme)

</div>

# Trabajo Práctico 2 - Algoritmos y Programación 3: **AlgoStar**

Trabajo Práctico número 2 de la materia _Algoritmos y Programación III_ [75.07 - 95.02] de FIUBA.

La documentación, los nombres de variables y los nombres de funciones están escritos principalmente en español ya que es el idioma en el que se dicta el curso.

## Breve resumen

El trabajo práctico consiste en desarrollar un juego por turnos inspirado en el famoso videojuego Starcraft (de ahí el nombre Algo (Algoritmos) - Star (Starcraft)), el cual se centra en la guerra entre imperios, la estrategia y se basa en la construcción y administración de un imperio. Este consta de un modelo de clases, sonidos e interfaz gráfica, acompañados de una suite de pruebas unitarias e integrales (casos de uso del juego). La implementación fue escrita en el lenguaje de programación de tipado estático Java, versión 11.0.7. Para el desarrollo del modelo de la solución se aplicaron principios de programación orientada a objetos y se trabajó con las técnicas de TDD (Test Driven Development) y CI (Continuous Integration), también se aplicaron patrones de diseño donde se consideró conveniente para resolver un problema específico.

## Grupo 5

* **Integrante 1** - [Iván Azuaje Ayala](https://github.com/iazuaje)
* **Integrante 2** - [Gabriel Zitelli](https://github.com/gabrielzitelli)
* **Integrante 3** - [Pedro Etchegoyen](https://github.com/PedroEtche)
* **Integrante 4** - [Walter Gabriel Diem](https://github.com/walgab)
* **Integrante 5** - [Benzaquen Ezequiel](https://github.com/ezebenza2000)

**Corrector:** [Pablo Rodríguez Massuh](https://github.com/xpitr256)

## Software usado

| Software | Versión |
| -------- | ------- |
| Java SDK | 11.0.7  |
| JavaFX   | 19      |
| JUnit    | 5.4     |
| Maven    | 3.8.6   |

## Aplicación

La versión más reciente de la aplicación puede encontrarse en la pestaña [releases](https://github.com/walgab/AlgoStar/releases/latest) y el archivo `.jar` se puede ejecutar en Linux, Windows o Mac de esta forma:

```bash
$ java -jar <archivo.jar>
```

Para usuarios de Windows se provee un archivo ejecutable `.exe`, que también está en la pestaña [releases](https://github.com/walgab/AlgoStar/releases/latest), funciona como una versión completamente contenida del juego, es decir, que no se necesita tener un JRE (Java Runtime Environment) instalado para ejecutar el archivo, ya que viene incluido. Sólo se necesita ejecutar el archivo y ya es jugable.

_Advertencia_: El archivo ejecutable puede activar una advertencia de su software de antivirus. Este es un problema conocido y se debe al hecho de que el archivo ejecutable está firmado con un certificado autofirmado. Esto no significa que el archivo sea inseguro o malicioso. Si desea continuar ejecutando el archivo ejecutable, es posible que deba agregar una excepción para el archivo en su software de antivirus o desactivar temporalmente el antivirus. Recomendamos solo desactivar temporalmente el antivirus y después de ejecutar el archivo, recuerde encenderlo de nuevo. Como regla general, se aconseja solo ejecutar archivos ejecutables de fuentes de confianza. Lamento cualquier inconveniente que esto pueda causar. Si tiene alguna preocupación o pregunta, no dude en ponerse en contacto.

Este ejecutable para Windows fue hecho creando un launcher para el archivo `.jar` con [Launch4J](https://launch4j.sourceforge.net/) y vinculándolo con el correspondiente JRE (versión 11.0.18). Luego se empaquetó esto en in archivo SFX (Self-Extracting Archive) con [7-Zip](https://www.7-zip.org/) y [7-Zip SFX Maker](https://sourceforge.net/projects/sfx-maker/), resultando en un archivo `.exe` que extrae el JRE y el juego mismo en una carpeta temporal (en el directorio `TEMP` de Windows) para que se pueda correr.

## Desarrollo

Aquellos que deseen colaborar con el proyecto pueden consultar la [guía de desarrollo](./docs/Desarrollo.md).

## Informe

El informe se puede encontrar en el siguiente [enlace](./docs/Informe_TP2_AlgoStar_Algoritmos_III_Suarez.pdf).

## Licencia

Este repositorio se encuentra bajo la Licencia MIT.

## Imágenes

![Imagen 1](./docs/img/img1.jpg)

![Imagen 2](./docs/img/img2.jpg)

![Imagen 3](./docs/img/img3.jpg)

![Imagen 4](./docs/img/img4.png)

![Imagen 5](./docs/img/img5.png)

![Imagen 6](./docs/img/img6.png)

![Imagen 7](./docs/img/img7.png)