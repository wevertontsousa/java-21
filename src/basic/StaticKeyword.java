package basic;

/*
 * Normalmente tem o construtor privado, para não ser instanciada
 *
 * Esse padrão foge a regra da POO de certa forma, mas faz parte dela
 *
 * Possuem métodos utilitários
 *
 * Atributos e métodos definidos como estáticos são carregados na memória
 * (Class Loader) na primeira vez que a classe é carregada pela JVM, ou seja, em
 * tempo de execução
 *
 * Não tem acesso a membros de instância (this)
 *
 * Alguns exemplos do próprio Java são: Arrays, Math, Streams, Collections
 */
public class StaticKeyword {
  public static int foo;

  static { // Bloco para inicializar atributos estáticos
    foo = 10;
  }

  public static int bar(int otherValue) { // Método para acessar atributos estáticos
    return foo + otherValue;
  }

}
