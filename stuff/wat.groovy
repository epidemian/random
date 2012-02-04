// Black magic starts here -----------------------------------------------------
void println(obj) { System.out.println(obj) }

_log = []
def wat() {
    // println _log.join('\n') // Uncomment to debug
    def sentences = [], words = []
    def endSentence = {->
        if (words) sentences << words.join(' ')
        words = []
    }
    def n = 0, len = _log.size()
    while (n < len) {
        // Poor-man's pattern-matching.
        def pattern = _log[n][0] + (n + 1 < len ? ':' + _log[n + 1][0] : '')
        switch (pattern) {
            // Two words in a sentence.
            case 'property:method':
                words << _log[n + 1][1] << _log[n][1]
                n += 2
                break
            // A one-word sentence.
            case ~/property.*/:
                endSentence()
                sentences << _log[n][1]
                n++
                break
            // The last word in an odd-word sentence.
            case ~/nested-property.*/:
                words << _log[n][1]
                endSentence()
                n++
                break
            default:
                throw new Exception("Unexpected pattern $pattern")
        }
    }
    
    // In case the last sentence was an even-word one.
    endSentence()
    
    sentences.join('\n')
}

class Wat {
    def log
    def Wat(l) { log = l }
    def methodMissing(String name, args) { 
        log << ['method', name]
        this
    }
    def propertyMissing(String name) {
        log << ['nested-property', name]
        null
    }
}

def methodMissing(String name, args) {
    _log << ['method', name]
    new Wat(_log)
}

def propertyMissing(String name) {
    _log << ['property', name]
}

// End of black magic. Start of fun! -------------------------------------------

groovy can record bare words
which is a really usefull feature
maybe
or maybe not
at least I think so ツ

println wat()
