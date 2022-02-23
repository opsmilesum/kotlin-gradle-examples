package `class`.sealedclass

sealed class Expr
class Const(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(expr: Expr): Int {
    return when (expr) {
        is Const -> expr.value;
        is Sum -> eval(expr.left) + eval(expr.right)
    }
}

fun main() {
    val expr = Sum(Const(3), Const(4));
    println(eval(expr))
}