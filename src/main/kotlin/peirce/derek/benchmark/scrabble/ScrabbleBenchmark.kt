package peirce.derek.benchmark.scrabble

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.Measurement
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State
import kotlinx.benchmark.Warmup
import org.openjdk.jmh.annotations.Fork
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = ["-Xms2G", "-Xmx2G"])
@Warmup(iterations = 3)
@Measurement(iterations = 10)
class ScrabbleBenchmark {
    private val scrabbleBase = readScrabbleBaseFrom("/shakespeare.txt", "/scrabble.txt")

    @Benchmark
    fun kotlinScrabbleFunctional(): List<Map.Entry<Int, List<String>>> {
        return KotlinScrabbleFunctional(scrabbleBase).run()
    }

    @Benchmark
    fun kotlinScrabbleImperative(): List<Map.Entry<Int, List<String>>> {
        return KotlinScrabbleImperative(scrabbleBase).run()
    }
}