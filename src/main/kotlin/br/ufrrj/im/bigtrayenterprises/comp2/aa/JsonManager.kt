package br.ufrrj.im.bigtrayenterprises.comp2.aa

import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.AICharacter
import br.ufrrj.im.bigtrayenterprises.comp2.aa.characters.Player
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.BattleChoice
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.TriggersChoice
import br.ufrrj.im.bigtrayenterprises.comp2.aa.events.BattleEvent
import br.ufrrj.im.bigtrayenterprises.comp2.aa.events.Event
import br.ufrrj.im.bigtrayenterprises.comp2.aa.events.TriggersEvent
import br.ufrrj.im.bigtrayenterprises.comp2.aa.items.Item
import br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers.AddItemTrigger
import br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers.ChangeHealthTrigger
import br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers.ModifierTrigger
import br.ufrrj.im.bigtrayenterprises.comp2.aa.triggers.Trigger
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory
import java.io.FileReader
import java.util.*

object JsonManager {
    val gson: Gson

    init {
        val triggerAdapterFactory = RuntimeTypeAdapterFactory.of(Trigger::class.java, "type")
                .registerSubtype(AddItemTrigger::class.java)
                .registerSubtype(ChangeHealthTrigger::class.java)
                .registerSubtype(ModifierTrigger::class.java)

        val choiceAdapterFactory = RuntimeTypeAdapterFactory.of(Choice::class.java, "type")
                .registerSubtype(BattleChoice::class.java)
                .registerSubtype(TriggersChoice::class.java)

        gson = GsonBuilder()
                .registerTypeAdapter<Book> {
                    deserialize {
                        // TODO: deserialize player
                        val p = Player(AttributeBuilder().createAttributes())
                        Book(it.json["description"].string, it.json["start"].string, p)
                    }
                }
                .registerTypeAdapter<Event> {
                    deserialize {
                        when (it.json["type"].string) {
                            "BattleEvent" -> {
                                val enemyClass = Class.forName(it.json["enemy"]["className"].string)

                                if (!AICharacter::class.java.isAssignableFrom(enemyClass)) {
                                    throw Exception("Reflected className was not an AICharacter")
                                }

                                val ctor = enemyClass.getConstructor()
                                val obj = ctor.newInstance()

                                BattleEvent(it.json["postBattleEvent"].string, obj as AICharacter, null)
                            }
                            "TriggersEvent" -> {
                                var choices = ArrayList<Choice>()
                                var triggers = ArrayList<Trigger>()

                                for (choice in it.json["choices"].array) {
                                    choices.add(it.context.deserialize(choice))
                                }

                                for (trigger in it.json["triggers"].array) {
                                    triggers.add(it.context.deserialize(trigger))
                                }

                                TriggersEvent(choices, it.json["description"].string, triggers)
                            }
                            else -> throw JsonParseException("Unknown event type")
                        }
                    }
                }
                .registerTypeAdapter<AddItemTrigger> {
                    deserialize {
                        val itemClass = Class.forName(it.json["itemClass"].string)

                        if (!Item::class.java.isAssignableFrom(itemClass)) {
                            throw Exception("Reflected itemClass was not an Item")
                        }

                        val ctor = itemClass.getConstructor()
                        val obj = ctor.newInstance()

                        AddItemTrigger(obj as Item)
                    }
                }
                .registerTypeAdapterFactory(triggerAdapterFactory)
                .registerTypeAdapterFactory(choiceAdapterFactory)
                .create()
    }

    fun deserializeBook(path: String): Book {
        return gson.fromJson<Book>(JsonReader(FileReader(path)))
    }

    fun deserializeEvent(path: String, player: Player): Event {
        val ev = gson.fromJson<Event>(JsonReader(FileReader(path)))

        if (ev is BattleEvent) {
            ev.player = player
        }

        return ev
    }
}