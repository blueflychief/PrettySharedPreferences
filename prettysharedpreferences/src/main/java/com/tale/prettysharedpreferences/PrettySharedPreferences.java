package com.tale.prettysharedpreferences;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by TALE on 9/11/2014.
 */
public abstract class PrettySharedPreferences<T extends PrettySharedPreferences> {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editing;
    private static final Map<String, TypeEditor> TYPE_EDITOR_MAP = new Hashtable<String, TypeEditor>();

    public PrettySharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * Call to get a {@link com.tale.prettysharedpreferences.StringEditor} object for the specific
     * key. <code>NOTE:</code> There is a unique {@link com.tale.prettysharedpreferences.TypeEditor}
     * object for a unique key.
     *
     * @param key The name of the preference.
     * @return {@link com.tale.prettysharedpreferences.StringEditor} object to be store or retrieve
     * a {@link java.lang.String} value.
     */
    protected StringEditor getStringEditor(String key) {
        TypeEditor typeEditor = TYPE_EDITOR_MAP.get(key);
        if (typeEditor == null) {
            typeEditor = new StringEditor(this, sharedPreferences, key);
            TYPE_EDITOR_MAP.put(key, typeEditor);
        } else if (!(typeEditor instanceof StringEditor)) {
            throw new IllegalArgumentException(String.format("key %s is already used for other type", key));
        }
        return (StringEditor) typeEditor;
    }

    /**
     * Call to get a {@link com.tale.prettysharedpreferences.IntegerEditor} object for the specific
     * key. <code>NOTE:</code> There is a unique {@link com.tale.prettysharedpreferences.TypeEditor}
     * object for a unique key.
     *
     * @param key The name of the preference.
     * @return {@link com.tale.prettysharedpreferences.IntegerEditor} object to be store or retrieve
     * a {@link java.lang.Integer} value.
     */
    protected IntegerEditor getIntegerEditor(String key) {
        TypeEditor typeEditor = TYPE_EDITOR_MAP.get(key);
        if (typeEditor == null) {
            typeEditor = new IntegerEditor(this, sharedPreferences, key);
            TYPE_EDITOR_MAP.put(key, typeEditor);
        } else if (!(typeEditor instanceof IntegerEditor)) {
            throw new IllegalArgumentException(String.format("key %s is already used for other type", key));
        }
        return (IntegerEditor) typeEditor;
    }

    /**
     * Call to get a {@link com.tale.prettysharedpreferences.BooleanEditor} object for the specific
     * key. <code>NOTE:</code> There is a unique {@link com.tale.prettysharedpreferences.TypeEditor}
     * object for a unique key.
     * @param key The name of the preference.
     * @return {@link com.tale.prettysharedpreferences.BooleanEditor} object to be store or retrieve
     * a {@link java.lang.Boolean} value.
     */
    protected BooleanEditor getBooleanEditor(String key) {
        TypeEditor typeEditor = TYPE_EDITOR_MAP.get(key);
        if (typeEditor == null) {
            typeEditor = new BooleanEditor(this, sharedPreferences, key);
            TYPE_EDITOR_MAP.put(key, typeEditor);
        } else if (!(typeEditor instanceof BooleanEditor)) {
            throw new IllegalArgumentException(String.format("key %s is already used for other type", key));
        }
        return (BooleanEditor) typeEditor;

    }

    /**
     * Call to get a {@link com.tale.prettysharedpreferences.LongEditor} object for the specific
     * key. <code>NOTE:</code> There is a unique {@link com.tale.prettysharedpreferences.TypeEditor}
     * object for a unique key.
     * @param key The name of the preference.
     * @return {@link com.tale.prettysharedpreferences.LongEditor} object to be store or retrieve
     * a {@link java.lang.Long} value.
     */
    protected LongEditor getLongEditor(String key) {
        TypeEditor typeEditor = TYPE_EDITOR_MAP.get(key);
        if (typeEditor == null) {
            typeEditor = new LongEditor(this, sharedPreferences, key);
            TYPE_EDITOR_MAP.put(key, typeEditor);
        } else if (!(typeEditor instanceof LongEditor)) {
            throw new IllegalArgumentException(String.format("key %s is already used for other type", key));
        }
        return (LongEditor) typeEditor;
    }

    /**
     * Call to get a {@link com.tale.prettysharedpreferences.FloatEditor} object for the specific
     * key. <code>NOTE:</code> There is a unique {@link com.tale.prettysharedpreferences.TypeEditor}
     * object for a unique key.
     * @param key The name of the preference.
     * @return {@link com.tale.prettysharedpreferences.FloatEditor} object to be store or retrieve
     * a {@link java.lang.Float} value.
     */
    protected FloatEditor getFloatEditor(String key) {
        TypeEditor typeEditor = TYPE_EDITOR_MAP.get(key);
        if (typeEditor == null) {
            typeEditor = new FloatEditor(this, sharedPreferences, key);
            TYPE_EDITOR_MAP.put(key, typeEditor);
        } else if (!(typeEditor instanceof FloatEditor)) {
            throw new IllegalArgumentException(String.format("key %s is already used for other type", key));
        }
        return (FloatEditor) typeEditor;
    }

    /**
     * Call to get a {@link com.tale.prettysharedpreferences.DoubleEditor} object for the specific
     * key. <code>NOTE:</code> There is a unique {@link com.tale.prettysharedpreferences.TypeEditor}
     * object for a unique key.
     * @param key The name of the preference.
     * @return {@link com.tale.prettysharedpreferences.DoubleEditor} object to be store or retrieve
     * a {@link java.lang.Double} value.
     */
    protected DoubleEditor getDoubleEditor(String key) {
        TypeEditor typeEditor = TYPE_EDITOR_MAP.get(key);
        if (typeEditor == null) {
            typeEditor = new DoubleEditor(this, sharedPreferences, key);
            TYPE_EDITOR_MAP.put(key, typeEditor);
        } else if (!(typeEditor instanceof DoubleEditor)) {
            throw new IllegalArgumentException(String.format("key %s is already used for other type", key));
        }
        return (DoubleEditor) typeEditor;

    }

    /**
     * Call to clear all values which is hold by this.
     *
     * @return Returns a reference to the same object, so you can chain put calls together.
     * @see android.content.SharedPreferences.Editor#clear()
     */
    public T clear() {
        editor().clear();
        return (T) this;
    }

    /**
     * Call to discard all changes but not apply or commit yet.
     *
     * @return Returns a reference to the same object, so you can chain put calls together.
     */
    public T discard() {
        if (editing != null) {
            editing = null;
        }
        return (T) this;
    }

    /**
     * Call to apply changes.
     * @see android.content.SharedPreferences.Editor#apply()
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void apply() {
        if (editing == null) {
            return;
        }
        try {
            editing.apply();
        } catch (Exception ex) {
            editing.commit(); // Fallback in case low api lever.
        }
        editing = null;
    }

    /**
     * Call to commit changes.
     * @see android.content.SharedPreferences.Editor#commit()
     */
    public boolean commit() {
        if (editing == null) {
            return false;
        }
        final boolean result = editing.commit();
        editing = null;
        return result;
    }

    synchronized SharedPreferences.Editor editor() {
        if (editing == null) {
            editing = sharedPreferences.edit();
        }
        return editing;
    }

}
