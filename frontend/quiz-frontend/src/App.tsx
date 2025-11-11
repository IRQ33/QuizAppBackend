import { BookOpen, Brain, Globe, Sparkles, ArrowRight, Check } from 'lucide-react';
import Header from "./components/Header.tsx";

function App() {

  return (
    <>
        <div className="min-h-screen bg-gradient-to-br from-emerald-50 via-teal-50 to-cyan-50">
        <Header></Header>
        <main>
            <section className="container mx-auto px-6 py-20">
                <div className="max-w-4xl mx-auto text-center">
                    <div className="inline-flex items-center gap-2 bg-white/60 backdrop-blur-sm px-4 py-2 rounded-full mb-6 shadow-sm">
                        <Sparkles className="w-4 h-4 text-emerald-600" />
                        <span className="text-sm font-medium text-gray-700">Nauka przez powtarzanie</span>
                    </div>

                    <h1 className="text-6xl font-bold mb-6 leading-tight">
                        Opanuj języki obce
                        <span className="block bg-gradient-to-r from-emerald-600 via-teal-600 to-cyan-600 bg-clip-text text-transparent">
                z inteligentnymi fiszkami
              </span>
                    </h1>

                    <p className="text-xl text-gray-600 mb-10 max-w-2xl mx-auto leading-relaxed">
                        Wykorzystaj moc nauki przestrzennej i adaptacyjnych algorytmów, aby szybko i efektywnie zapamiętać nowe słówka i zwroty.
                    </p>

                    <div className="flex items-center justify-center gap-4 mb-16">
                        <button className="bg-gradient-to-r from-emerald-500 to-teal-500 text-white px-8 py-4 rounded-full hover:shadow-xl hover:scale-105 transition-all duration-200 font-semibold text-lg flex items-center gap-2">
                            Zacznij za darmo
                            <ArrowRight className="w-5 h-5" />
                        </button>
                        
                    </div>

                    <div className="grid grid-cols-3 gap-8 max-w-2xl mx-auto">
                        <div className="text-center">
                            <div className="text-3xl font-bold text-emerald-600 mb-1">10M+</div>
                            <div className="text-sm text-gray-600">Fiszek w bazie</div>
                        </div>
                        <div className="text-center">
                            <div className="text-3xl font-bold text-emerald-600 mb-1">50+</div>
                            <div className="text-sm text-gray-600">Języków</div>
                        </div>
                        <div className="text-center">
                            <div className="text-3xl font-bold text-emerald-600 mb-1">98%</div>
                            <div className="text-sm text-gray-600">Zadowolonych użytkowników</div>
                        </div>
                    </div>
                </div>
            </section>

            <section id="features" className="container mx-auto px-6 py-20">
                <div className="max-w-6xl mx-auto">
                    <div className="text-center mb-16">
                        <h2 className="text-4xl font-bold mb-4">Dlaczego FlashLearn?</h2>
                        <p className="text-gray-600 text-lg">Wszystko czego potrzebujesz do efektywnej nauki</p>
                    </div>

                    <div className="grid md:grid-cols-3 gap-8">
                        <div className="bg-white/60 backdrop-blur-sm rounded-3xl p-8 hover:shadow-xl transition-all duration-300 hover:-translate-y-1">
                            <div className="w-14 h-14 bg-gradient-to-br from-emerald-100 to-teal-100 rounded-2xl flex items-center justify-center mb-6">
                                <Brain className="w-7 h-7 text-emerald-600" />
                            </div>
                            <h3 className="text-xl font-bold mb-3">Inteligentne powtórki</h3>
                            <p className="text-gray-600 leading-relaxed">
                                Algorytm dostosowuje się do Twojego tempa nauki, pokazując trudniejsze słówka częściej.
                            </p>
                        </div>

                        <div className="bg-white/60 backdrop-blur-sm rounded-3xl p-8 hover:shadow-xl transition-all duration-300 hover:-translate-y-1">
                            <div className="w-14 h-14 bg-gradient-to-br from-teal-100 to-cyan-100 rounded-2xl flex items-center justify-center mb-6">
                                <Globe className="w-7 h-7 text-teal-600" />
                            </div>
                            <h3 className="text-xl font-bold mb-3">50+ języków</h3>
                            <p className="text-gray-600 leading-relaxed">
                                Od angielskiego po japoński - ucz się dowolnego języka z gotowych zestawów fiszek.
                            </p>
                        </div>

                        <div className="bg-white/60 backdrop-blur-sm rounded-3xl p-8 hover:shadow-xl transition-all duration-300 hover:-translate-y-1">
                            <div className="w-14 h-14 bg-gradient-to-br from-cyan-100 to-emerald-100 rounded-2xl flex items-center justify-center mb-6">
                                <BookOpen className="w-7 h-7 text-cyan-600" />
                            </div>
                            <h3 className="text-xl font-bold mb-3">Własne zestawy</h3>
                            <p className="text-gray-600 leading-relaxed">
                                Twórz personalizowane zestawy fiszek dostosowane do Twoich potrzeb i celów.
                            </p>
                        </div>
                    </div>
                </div>
            </section>

            <section id="how-it-works" className="container mx-auto px-6 py-20">
                <div className="max-w-4xl mx-auto">
                    <div className="text-center mb-16">
                        <h2 className="text-4xl font-bold mb-4">Jak to działa?</h2>
                        <p className="text-gray-600 text-lg">Trzy proste kroki do sukcesu</p>
                    </div>

                    <div className="space-y-8">
                        <div className="flex items-start gap-6 bg-white/60 backdrop-blur-sm rounded-3xl p-8 hover:shadow-lg transition-all duration-300">
                            <div className="flex-shrink-0 w-12 h-12 bg-gradient-to-br from-emerald-500 to-teal-500 rounded-full flex items-center justify-center text-white font-bold text-lg">
                                1
                            </div>
                            <div>
                                <h3 className="text-xl font-bold mb-2">Wybierz język i zestaw</h3>
                                <p className="text-gray-600 leading-relaxed">
                                    Przeglądaj naszą bogatą bibliotekę zestawów fiszek lub stwórz własny dostosowany do Twoich potrzeb.
                                </p>
                            </div>
                        </div>

                        <div className="flex items-start gap-6 bg-white/60 backdrop-blur-sm rounded-3xl p-8 hover:shadow-lg transition-all duration-300">
                            <div className="flex-shrink-0 w-12 h-12 bg-gradient-to-br from-emerald-500 to-teal-500 rounded-full flex items-center justify-center text-white font-bold text-lg">
                                2
                            </div>
                            <div>
                                <h3 className="text-xl font-bold mb-2">Ucz się codziennie</h3>
                                <p className="text-gray-600 leading-relaxed">
                                    Poświęć 10-15 minut dziennie na przeglądanie fiszek. System automatycznie dostosuje tempo do Twojego postępu.
                                </p>
                            </div>
                        </div>

                        <div className="flex items-start gap-6 bg-white/60 backdrop-blur-sm rounded-3xl p-8 hover:shadow-lg transition-all duration-300">
                            <div className="flex-shrink-0 w-12 h-12 bg-gradient-to-br from-emerald-500 to-teal-500 rounded-full flex items-center justify-center text-white font-bold text-lg">
                                3
                            </div>
                            <div>
                                <h3 className="text-xl font-bold mb-2">Śledź swój postęp</h3>
                                <p className="text-gray-600 leading-relaxed">
                                    Obserwuj jak Twoja wiedza rośnie dzięki szczegółowym statystykom i wykresom postępów.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section className="container mx-auto px-6 py-20">
                <div className="max-w-4xl mx-auto">
                    <div className="bg-gradient-to-r from-emerald-500 via-teal-500 to-cyan-500 rounded-3xl p-12 text-center text-white shadow-2xl">
                        <h2 className="text-4xl font-bold mb-4">Gotowy na rozpoczęcie nauki?</h2>
                        <p className="text-xl mb-8 text-emerald-50">
                            Dołącz do tysięcy użytkowników, którzy już poprawiają swoje umiejętności językowe
                        </p>
                        <button className="bg-white text-emerald-600 px-8 py-4 rounded-full hover:shadow-xl hover:scale-105 transition-all duration-200 font-semibold text-lg flex items-center gap-2 mx-auto">
                            Rozpocznij za darmo
                            <ArrowRight className="w-5 h-5" />
                        </button>

                        <div className="flex items-center justify-center gap-6 mt-8 text-emerald-50">
                            <div className="flex items-center gap-2">
                                <Check className="w-5 h-5" />
                                <span>Bezpłatny start</span>
                            </div>
                            <div className="flex items-center gap-2">
                                <Check className="w-5 h-5" />
                                <span>Bez karty kredytowej</span>
                            </div>
                            <div className="flex items-center gap-2">
                                <Check className="w-5 h-5" />
                                <span>Bez żadnych subskrypcji</span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        </div>

    </>
  )
}

export default App
