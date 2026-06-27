This uses Kotlin to run the [Reactive Scrabble Benchmarks](https://kotlin.link/articles/The-Reactive-Scrabble-benchmarks.html).

- Functional: uses Kotlin's `Sequence` and `Grouping` methods for shorter code
- Imperative: rewrites using lengthier, but more efficient code.

```
main summary:
Benchmark                                   Mode  Cnt    Score   Error  Units
ScrabbleBenchmark.kotlinScrabbleFunctional  avgt   10  274.045 ∩┐╜ 4.697  ms/op
ScrabbleBenchmark.kotlinScrabbleImperative  avgt   10  187.903 ∩┐╜ 4.047  ms/op
```
