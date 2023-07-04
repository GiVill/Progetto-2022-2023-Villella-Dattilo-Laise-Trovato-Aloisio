package com.example.vintedandroid.model.dao;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.example.vintedandroid.model.dto.BasicInsertionDto;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BasicInsertionDao_Impl implements BasicInsertionDao {
  private final RoomDatabase __db;

  public BasicInsertionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<BasicInsertionDto> getAll() {
    final String _sql = "select * from basicInsertionDto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "condition");
      final int _cursorIndexOfCreationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "creationDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
      final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final List<BasicInsertionDto> _result = new ArrayList<BasicInsertionDto>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BasicInsertionDto _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final int _tmpPrice;
        _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpCondition;
        if (_cursor.isNull(_cursorIndexOfCondition)) {
          _tmpCondition = null;
        } else {
          _tmpCondition = _cursor.getString(_cursorIndexOfCondition);
        }
        final String _tmpCreationDate;
        if (_cursor.isNull(_cursorIndexOfCreationDate)) {
          _tmpCreationDate = null;
        } else {
          _tmpCreationDate = _cursor.getString(_cursorIndexOfCreationDate);
        }
        final String _tmpEndDate;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmpEndDate = null;
        } else {
          _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        final String _tmpImagePath;
        if (_cursor.isNull(_cursorIndexOfImagePath)) {
          _tmpImagePath = null;
        } else {
          _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
        }
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item = new BasicInsertionDto(_tmpId,_tmpTitle,_tmpPrice,_tmpDescription,_tmpCondition,_tmpCreationDate,_tmpEndDate,_tmpImagePath,_tmpUserId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public BasicInsertionDto getById(final long basicId) {
    final String _sql = "select * from basicInsertionDto where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, basicId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "condition");
      final int _cursorIndexOfCreationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "creationDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
      final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final BasicInsertionDto _result;
      if(_cursor.moveToFirst()) {
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final int _tmpPrice;
        _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpCondition;
        if (_cursor.isNull(_cursorIndexOfCondition)) {
          _tmpCondition = null;
        } else {
          _tmpCondition = _cursor.getString(_cursorIndexOfCondition);
        }
        final String _tmpCreationDate;
        if (_cursor.isNull(_cursorIndexOfCreationDate)) {
          _tmpCreationDate = null;
        } else {
          _tmpCreationDate = _cursor.getString(_cursorIndexOfCreationDate);
        }
        final String _tmpEndDate;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmpEndDate = null;
        } else {
          _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        final String _tmpImagePath;
        if (_cursor.isNull(_cursorIndexOfImagePath)) {
          _tmpImagePath = null;
        } else {
          _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
        }
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _result = new BasicInsertionDto(_tmpId,_tmpTitle,_tmpPrice,_tmpDescription,_tmpCondition,_tmpCreationDate,_tmpEndDate,_tmpImagePath,_tmpUserId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
